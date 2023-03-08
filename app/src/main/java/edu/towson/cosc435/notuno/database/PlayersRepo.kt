package edu.towson.cosc435.notuno.database
import edu.towson.cosc435.notuno.model.OnlinePlayer

//Interface for PlayersDatabaseRepository
interface IPlayersDatabaseRepository{
    suspend fun getPlayers(): List<OnlinePlayer>
    suspend fun addPlayer(player: OnlinePlayer)
}//end of interface