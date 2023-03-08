package edu.towson.cosc435.notuno.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.towson.cosc435.notuno.R.drawable.*


@Composable
fun Tutorial(app: Context) {
    Box(
        modifier = Modifier
            .verticalScroll(ScrollState(1))
            .background(Color.Black)
    ) {
        Column(modifier = Modifier.padding(start = 4.dp)) {
            run {

            }
            Card(backgroundColor = Color.DarkGray) {
                Column() {
                    Text(text = "Deal", fontSize = 40.sp, color = Color.LightGray)
                    Text(
                        text = "Seven cards to each player, one card at a time. Place the remaining deck (draw pile) in the middle and flip over the top card to start the discard pile.",
                        fontSize = 15.sp, color = Color.LightGray
                    )

                }

            }


            Card(backgroundColor = Color.DarkGray) {
                Column() {
                    Text(
                        text = "Objective", fontSize = 40.sp,
                        color = Color.LightGray
                    )
                    Text(
                        text = "The object of the game is to be the first player to get rid of all the cards in your hand each round. Cards that match the top discard in color or number can be played into the middle.",
                        fontSize = 15.sp, color = Color.LightGray
                    )

                }

            }


            Card(backgroundColor = Color.DarkGray) {
                Column() {
                    Text(text = "Special Cards", fontSize = 40.sp, color = Color.LightGray)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column(modifier = Modifier.padding(end = 0.dp)) {
                            //    R.drawable.gstop
                            Image(
                                painter = painterResource(id = rstop),
                                contentDescription = "skip card",
                                modifier = Modifier
                                    .size(120.dp)
                                    .clip(RoundedCornerShape(40.dp)),
                            )

                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Skip:",
                                fontSize = 25.sp,
                                modifier = Modifier.padding(end = 210.dp), color = Color.LightGray
                            )
                            Text(
                                text = "When played, the next player's turn is skipped. The Skip card can be played on a matching color discard or a previously played Skip. If a Skip is the first discard flipped, the player left of the dealer is skipped, and the next player begins play.",
                                color = Color.LightGray
                            )

                        }
                    }
                    /*

                 */
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column(modifier = Modifier.padding(end = 0.dp)) {

                            Image(
                                painter = painterResource(id = rreverse),
                                contentDescription = "skip card",
                                modifier = Modifier
                                    .size(120.dp)
                                    .clip(RoundedCornerShape(40.dp))
                            )

                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Reverse:",
                                fontSize = 25.sp,
                                modifier = Modifier.padding(end = 164.dp), color = Color.LightGray
                            )
                            Text(
                                text = "When played, the direction of play is reversed. The Reverse card can be played on a matching color discard or a previously played Reverse. If a Reverse is the first discard flipped, the dealer begins play and play continues counter-clockwise.",
                                color = Color.LightGray
                            )

                        }
                    }
                    /*

                 */
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column(modifier = Modifier.padding(end = 0.dp)) {

                            Image(
                                painter = painterResource(id = rdraw2),
                                contentDescription = "skip card",
                                modifier = Modifier
                                    .size(120.dp)
                                    .clip(RoundedCornerShape(40.dp))
                            )

                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Draw 2:",
                                fontSize = 25.sp,
                                modifier = Modifier.padding(end = 180.dp), color = Color.LightGray
                            )
                            Text(
                                text = "When played, the next player must draw two cards and loses his/her turn to play. The Draw 2 card can be played on a matching color discard or a previously played Draw 2. If a Draw 2 is the first discard, the player left of the dealer draws two cards and loses his/her turn to play.",
                                color = Color.LightGray
                            )
                        }
                    }


                    /*

                 */

                }

            }


            Card(backgroundColor = Color.DarkGray) {
                Column() {
                    Text(text = "Game Play", fontSize = 40.sp, color = Color.LightGray)
                    Text(
                        text = "The player left of the dealer is first to play. A card can be played into the middle if it matches the top discard in color or number, or a special card can be played (see above). If a player cannot play, a card is drawn from the draw pile. If the card drawn can be played, the player can play it.\n" +
                                "\n" +
                                "Once a player is down to one card left, he/she must announce “Uno.” Uno should be announced as a player is playing his/her second to last card. If a player fails to announce Uno with only one card left, another player can catch it by calling Uno before the next player plays a card. If caught not saying Uno before the next card is played, the player with one card must draw two cards from the draw pile. The first player to get rid of all his/her cards wins the round.",
                        color = Color.LightGray
                    )
                }
            }


            Card(backgroundColor = Color.DarkGray) {
                Column() {
                    Text(
                        text = "Score", fontSize = 40.sp,
                        color = Color.LightGray
                    )
                    Text(
                        text = "The player that got rid of his/her cards first is awarded points based on the cards remaining in the other players’ hands.\n" +
                                "\n" +
                                "Cards 0-9: Face Value (i.e., a 5 is worth 5 points)\n" +
                                "\n" +
                                "Skip/Reverse/Draw 2: 20 points each\n" +
                                "\n" +
                                "The first player to 500 points wins the game.",
                        color = Color.LightGray
                    )
                }

            }



            Card(
                modifier = Modifier.padding(bottom = 0.dp),
                backgroundColor = Color.DarkGray
            ) {
                Column() {
                    Text(
                        text = "Rule", fontSize = 40.sp,
                        color = Color.LightGray
                    )
                    Text(
                        text = "If the draw pile runs out before a player wins the round, reshuffle the discard pile to make a new draw pile.",
                        color = Color.LightGray
                    )
                }

            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 60.dp), backgroundColor = Color.DarkGray
            )
            {
                Column(
                ) {

                    Text(
                        text = "Still need help", fontSize = 40.sp,
                        color = Color.LightGray
                    )
                    Text(
                        text = "Click here for not Uno support", modifier = Modifier.clickable {
                            performPhoneCall("3016558544", app);
                        },
                        color = Color.LightGray, textDecoration = TextDecoration.Underline
                    )
                }

            }
        }
    }
}

fun performPhoneCall(phoneNumber: String, app: Context) {
    val intent = Intent(Intent.ACTION_DIAL)
    intent.data = Uri.parse("tel:$phoneNumber")
    try {
        app.startActivity(intent)
    } catch (ex: Exception) {

    }
}