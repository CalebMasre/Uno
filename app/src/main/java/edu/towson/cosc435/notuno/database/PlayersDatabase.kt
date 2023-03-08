package edu.towson.cosc435.notuno.database

import androidx.room.*
import edu.towson.cosc435.notuno.model.OnlinePlayer
import java.util.*

@Dao
interface PlayersDao {

    //query that returns all the players in the database
    @Query("select * from players")
    suspend fun getPlayer(): List<OnlinePlayer>

    //query that inserts player into database
    @Insert
    suspend fun addPlayer(player: OnlinePlayer)

}//end of Players Dao

@Database(entities = [OnlinePlayer::class], version = 2, exportSchema = false)
abstract class PlayersDatabase : RoomDatabase() {

    abstract fun playersDao(): PlayersDao
}//end of Player database