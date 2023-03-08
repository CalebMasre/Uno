package edu.towson.cosc435.notuno.networking

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.gson.Gson
import edu.towson.cosc435.notuno.model.OnlinePlayer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.InputStream
import java.io.OutputStream


interface IPlayersFetcher {
    suspend fun fetchPlayers(): List<OnlinePlayer>
    suspend fun fetchAvatars(url: String): Bitmap?
    suspend fun fetchAndSave(url: String, file: OutputStream)
    suspend fun fetchBytes(url: String): InputStream?
}//end of IPlayersFetcher

class PlayersFetcher(ctx: Context) : IPlayersFetcher {

    //url that lead to a my json server that contains the Online players' information
    private val url = "https://my-json-server.typicode.com/iifahad/game-json/avatars"
    private val client = OkHttpClient.Builder()
        .cache(
            Cache(
                directory = ctx.cacheDir,
                maxSize = 10 * 1024L * 1024L
            )
        )
        .build()

    //function that fetches players with the my json server url
    override suspend fun fetchPlayers(): List<OnlinePlayer> {
        //returns a list of the players that are fetched from the my json server
        return withContext(IO) {
            val request = Request.Builder()
                .get()
                .url(url)
                .build()
            val response = client.newCall(request).execute()
            val responseBody = response.body

            if (responseBody != null) {
                val jsonString = responseBody.string()
                val gson = Gson()
                val avatarsArray = gson.fromJson(jsonString, Array<OnlinePlayer>::class.java)
                avatarsArray.toList()
            } else {
                listOf()
            }
        }
    }//end of fetchPlayers

    override suspend fun fetchAvatars(url: String): Bitmap? {
        val stream = fetchBytes(url)
        return BitmapFactory.decodeStream(stream)
    }//end of fetchAvatars

    override suspend fun fetchAndSave(url: String, file: OutputStream) {
        val stream = fetchBytes(url)
        file.bufferedWriter()
        val bytes = stream?.readBytes()
        if (bytes != null) {
            file.write(bytes)
        }
    }//end of fetchAndSave

    override suspend fun fetchBytes(url: String): InputStream? {
        return withContext(Dispatchers.IO) {
            val request = Request.Builder()
                .get()
                .url(url)
                .build()
            val response = client.newCall(request).execute()
            response.body?.byteStream()
        }
    }//end of fetchBytes

}//end of PlayerFetcher class