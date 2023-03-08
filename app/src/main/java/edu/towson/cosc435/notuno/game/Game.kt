package edu.towson.cosc435.notuno.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import edu.towson.cosc435.notuno.R
import edu.towson.cosc435.notuno.nav.Screens


@Composable
fun Game(numberOfPlayers: Int, gameViewModel: GameViewModel, navToHome: (String) -> Unit) {
    var openWinWindow by remember { mutableStateOf(true) }
    var hand by remember { mutableStateOf(gameViewModel.playerOneDeck) }
    var secondSize by remember { mutableStateOf(gameViewModel.player4Count) }
    var winner by remember { mutableStateOf(gameViewModel.winner) }
    var thirdSize by remember { mutableStateOf(gameViewModel.player3Count) }
    var fourthSize by remember { mutableStateOf(gameViewModel.player4Count) }
    var event by remember { mutableStateOf(gameViewModel.event) }

    //method that delivers the number of player to the game view model
    gameViewModel.setNumberOfPlayers(numberOfPlayers)

    //Column that contains all of the game's composable items
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(550.dp)
            .background(color = Color.Red)
    ) {
        Row(
            modifier = Modifier
                .height(400.dp)
                .width(360.dp)
        ) {
            //Box that represents a container showing the data of the other players and the events
            Box(
                modifier = Modifier
                    .height(400.dp)
                    .width(360.dp)
                    .padding(20.dp)
                    .background(Color.Black)
                    .offset(y = 15.dp)
            ) {
                //text for the events to appear on the game board
                gameViewModel.event.value?.let {
                    Text(
                        it, color = Color.White, modifier = Modifier
                            .align(Alignment.BottomCenter)
                    )
                }//end of event text composable

                Button(
                    onClick = {
                        gameViewModel.clearGame()
                        navToHome(Screens.Tit.route)
                    },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                ) {
                    Text("Exit")
                }

                //Image for Pile
                Image(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .height(50.dp)
                        .width(35.dp)
                        .offset(
                            x = (-35).dp
                        )
                        .clickable(enabled = true) {
                            gameViewModel.draw()
                            secondSize = gameViewModel.player2Count
                            thirdSize = gameViewModel.player3Count
                            fourthSize = gameViewModel.player4Count
                            hand = gameViewModel.playerOneDeck
                            winner = gameViewModel.winner
                            event = gameViewModel.event
                        },
                    painter = painterResource(id = R.drawable.unoback),
                    contentDescription = "back of uno card",
                )//end of pile image

                //Image for top Card
                gameViewModel.topCard.value?.let { painterResource(id = it.getImage()) }?.let {
                    Image(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .height(50.dp)
                            .width(35.dp)
                            .offset(
                                x = 35.dp
                            ),

                        painter = it,
                        contentDescription = "image for whatever the top card is",
                    )//end of top card image
                }

                //Image and Text fields for Second player Player
                Image(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .height(50.dp)
                        .width(35.dp)
                        .offset(y = 25.dp)
                        .graphicsLayer {
                            rotationZ = 180f
                        },
                    painter = painterResource(id = R.drawable.unoback),
                    contentDescription = "back of uno card",
                )//end of second player image

                //text for player 2 title on board
                Text(
                    text = "Player 2",
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                )//end of player 2 title

                //text for player 2 number of cards
                Text(
                    text = "${secondSize.value} cards",
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .offset(
                            y = 75.dp
                        )
                )//end of player 2 number of cards

                //if statement checks for if there are more than 2 player and shows player 3 deck
                if (numberOfPlayers > 2) {
                    //Image and Text fields for third Player
                    Image(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .height(50.dp)
                            .width(35.dp)
                            .graphicsLayer {
                                rotationZ = 270f
                            },
                        painter = painterResource(id = R.drawable.unoback),
                        contentDescription = "back of uno card",
                    )//end of player 3 image

                    Text(
                        text = "Player 3",
                        color = Color.White,
                        modifier = Modifier
                            .offset(
                                y = 130.dp
                            )
                    )//end of player 3 title

                    Text(
                        text = "${thirdSize.value} cards",
                        color = Color.White,
                        modifier = Modifier
                            .offset(
                                y = 205.dp
                            )
                    )//end of player 3 card count
                }//end of player 3 if statement

                //if statement checks for if there are more than 3 player and shows player 4 deck
                if (numberOfPlayers > 3) {

                    //Image and Text fields for fourth Player
                    Image(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .height(50.dp)
                            .width(35.dp)
                            .graphicsLayer {
                                rotationZ = 90f
                            },
                        painter = painterResource(id = R.drawable.unoback),
                        contentDescription = "back of uno card"

                        )//end of player 4 image

                    Text(
                        text = "Player 4",
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .offset(y = (-40).dp)
                    )//end of player 4 title

                    Text(
                        text = "${fourthSize.value} cards",
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .offset(
                                y = 40.dp
                            )
                    )//end of player 4 card count

                }//end of player 4 if statement

            }//end of box representing game table

        }//end of row

        Row(
            modifier = Modifier
                .height(100.dp)
        ) {
            Row(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth(),
            ) {
                //Lazy row that shows the deck of the user
                LazyRow(
                    verticalAlignment = Alignment.Top

                ) {
                    //each value of the items represent a card in the player deck
                    items(hand.value!!) {
                        Box(
                            modifier = Modifier
                                .height(100.dp)
                                .width(100.dp)
                                .background(Color.Red)
                        ) {
                            Image(
                                //painterResource get the image variable from the card and prints it into the lazy row
                                painter = painterResource(id = it.getImage()),
                                contentDescription = "",
                                modifier = Modifier
                                    .height(120.dp)
                                    .width(100.dp)
                                    .clip(RoundedCornerShape(35.dp))
                                    .clickable {
                                        gameViewModel.place(it)
                                        secondSize = gameViewModel.player2Count
                                        thirdSize = gameViewModel.player3Count
                                        fourthSize = gameViewModel.player4Count
                                        hand = gameViewModel.playerOneDeck
                                        winner = gameViewModel.winner
                                        event = gameViewModel.event
                                    }
                            )//end of image for one of the users Cards

                        }//end of box

                    }//end of items function

                }//end of lazy row for the cards of the user

            }//end of row

        }//end of row

    }//end of Column that takes up all of the game screen


    /*If a winner value is changed in the game view model
    * then this function is called*/
    if (winner.value != -1 && openWinWindow) {
        //Alert Dialog that tells the user somebody has won
        AlertDialog(
            onDismissRequest = {
                openWinWindow = false
                gameViewModel.clearGame()
                navToHome(Screens.Tit.route)
            },
            title = {
                if (winner.value == 1)
                    Text(text = "Congratulations")
                else
                    Text(text = "Sorry")
            },
            text = {
                Text("Player ${winner.value} wins")
            },
            confirmButton = {

            },
            dismissButton = {

            }
        )//end of Alert Dialog for Win Screen
    }//end of winner if statements
}//end of game composable