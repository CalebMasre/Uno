package edu.towson.cosc435.notuno.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import edu.towson.cosc435.notuno.database.DatabaseViewModel
import edu.towson.cosc435.notuno.database.PlayersList

//Composable function that calls the PlayerList Composable
@ExperimentalFoundationApi
@Composable
fun PlayerListScreen (vm: DatabaseViewModel){
    val players by vm.players
    val waiting by vm.waiting

    PlayersList(players, onFetchImage = vm::fetchImage , waiting = waiting)
}//end of PlayerListScreen

