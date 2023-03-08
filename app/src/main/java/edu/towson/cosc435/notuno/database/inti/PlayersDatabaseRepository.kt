package edu.towson.cosc435.notuno.database.inti

import android.app.Application
import androidx.room.Room
import edu.towson.cosc435.notuno.database.IPlayersDatabaseRepository
import edu.towson.cosc435.notuno.database.PlayersDatabase
import edu.towson.cosc435.notuno.model.OnlinePlayer

class PlayersDatabaseRepository(app: Application) : IPlayersDatabaseRepository {
    private val db: PlayersDatabase

    //function that creates the Online Player database with Room
    init {
        db = Room.databaseBuilder(
            app,
            PlayersDatabase::class.java,
            "players.db"
        ).fallbackToDestructiveMigration()
            .build()
    }//end of init

    //function that returns all of the Online Players from the database
    override suspend fun getPlayers(): List<OnlinePlayer> {
        return db.playersDao().getPlayer()
    }//end of getPlayers

    //function that adds an Online Player to the database
    override suspend fun addPlayer(player: OnlinePlayer) {
        db.playersDao().addPlayer(player = player)
    }//end of addPlayer
}//end of PlayersDatabaseRepository class