package edu.towson.cosc435.notuno.database

import android.app.Application
import android.graphics.Bitmap
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import edu.towson.cosc435.notuno.database.inti.PlayersDatabaseRepository
import edu.towson.cosc435.notuno.model.OnlinePlayer
import edu.towson.cosc435.notuno.networking.PlayersFetcher
import kotlinx.coroutines.launch

class DatabaseViewModel(app: Application) : AndroidViewModel(app) {
    private val playerS: MutableState<List<OnlinePlayer>> = mutableStateOf(listOf())
    val players: State<List<OnlinePlayer>> = playerS
    private lateinit var repository: IPlayersDatabaseRepository
    private val playersFetcher = PlayersFetcher(getApplication())
    val waiting = mutableStateOf(false)


    /* Init function does the following:
    1. Initializes the database repository
    2. Fetches player data from our json server and puts it into a list
    3. Adds each players data from the player list into the repository
    */
    init {
        viewModelScope.launch {
            waiting.value = true
            //initialized database repository
            repository = PlayersDatabaseRepository(getApplication())
            try {
                //created list of online players
                val players = playersFetcher.fetchPlayers()
                //adds each player into our database repository
                players.forEach { player -> repository.addPlayer(player) }
            } catch (_: Exception) { }
            playerS.value = repository.getPlayers()
            waiting.value = false
        }//end of scope

    }//end of init

    //Function that fetches image. If theres an error, the function return null.
    suspend fun fetchImage(url: String): Bitmap? {
        return try {
            playersFetcher.fetchAvatars(url)
        } catch (e: Exception) {
            null
        }

    }//end of fetch image

}//end of database view-model