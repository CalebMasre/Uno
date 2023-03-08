package edu.towson.cosc435.notuno.dialog

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

@Composable
fun ConfirmDialog(number: Int, confirmViewModel: ConfirmViewModel) {
    val open by confirmViewModel.open
    if(open) {
        //Alert dialog composable that pops up when you try to start a game
        AlertDialog(
            dismissButton = {
                Button({ confirmViewModel.closeMessage() }) {
                    Text("Don't Start")
                }
            },
            onDismissRequest = { confirmViewModel.closeMessage() },
            title = { Text("Confirm Game") },
            text = { Text("Are you sure you want to start a game of $number players?")
            },
            confirmButton = {
                Button({ confirmViewModel.startGame()}) {
                    Text("Start")
                }
            }
        )//end of Alert Dialog composable

    }//end of if statement

}//end of Confirm Dialog composable