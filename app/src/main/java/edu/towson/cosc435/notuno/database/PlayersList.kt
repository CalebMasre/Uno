package edu.towson.cosc435.notuno.database

import android.graphics.Bitmap
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import edu.towson.cosc435.notuno.model.OnlinePlayer

@ExperimentalFoundationApi
@Composable
fun PlayersList(
    players: List<OnlinePlayer>,
    onFetchImage: suspend (String) -> Bitmap?,
    waiting: Boolean
) {
    //Composable Box that contains the entire list of players
    Box(
        modifier = Modifier
            .padding(bottom = 45.dp)
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //if waiting is true the loading indicator will pop up
            if (waiting)
                CircularProgressIndicator()
            else {
                //Lazy Column that traverses each player in the list
                LazyColumn {

                    itemsIndexed(players) { _, player ->
                        //sends each player in the list to the player row
                        PlayerRow(player = player, onFetchImage = onFetchImage)
                    }

                }//end of LazyColumn

            }//end of if-else statement

        }//end of Column

    }//end of Box Composable

}//end of PlayersList composable