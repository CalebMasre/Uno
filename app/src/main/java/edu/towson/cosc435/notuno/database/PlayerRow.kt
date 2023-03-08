package edu.towson.cosc435.notuno.database

import android.graphics.Bitmap
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import edu.towson.cosc435.notuno.model.OnlinePlayer

@ExperimentalFoundationApi
@Composable
fun PlayerRow(player: OnlinePlayer, onFetchImage: suspend (String) -> Bitmap?) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 12.dp,
        backgroundColor = Color.DarkGray,
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            //Text Columns that shows the players id
            Column(modifier = Modifier.weight(1.5f)) {
                Text(text = "Username:", color = Color.LightGray)
                Text(player.id, color = Color.LightGray)
            }//end of column

            //Column that shows the Image to the right of the username
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var avatar by remember { mutableStateOf<Bitmap?>(null) }
                LaunchedEffect(key1 = player.id) { avatar = onFetchImage(player.image_url!!) }
                //the image is null than it shows the loading indicator
                if (avatar == null)
                    CircularProgressIndicator()
                else {
                    Image(
                        bitmap = avatar!!.asImageBitmap(),
                        contentDescription = "Avatar"
                    )
                }
            }//end of column

        }//end of row

    }//end of Card

}//end of PlayerRow composable