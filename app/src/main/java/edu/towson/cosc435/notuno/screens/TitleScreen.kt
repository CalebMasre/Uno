package edu.towson.cosc435.notuno.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import edu.towson.cosc435.notuno.R

@Composable
fun TitleScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row {
            Image(
                modifier = Modifier
                    .height(350.dp)
                    .width(350.dp),
                painter = painterResource(id = R.drawable.notunologo),
                contentDescription = ""
            )
        }//end of row
    }//end of column
}//end of Title Screen Composable