package edu.towson.cosc435.notuno.game

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.towson.cosc435.notuno.game_objects.Card
import edu.towson.cosc435.notuno.game_objects.Player
import edu.towson.cosc435.notuno.R

class GameViewModel : ViewModel() {
    //non readable values that are connected to a readable value
    private var _playerOneDeck: MutableLiveData<List<Card>> = MutableLiveData(listOf())
    private var _player2Count: MutableLiveData<Int> = MutableLiveData(0)
    private var _player3Count: MutableLiveData<Int> = MutableLiveData(0)
    private var _player4Count: MutableLiveData<Int> = MutableLiveData(0)
    private var _winner: MutableLiveData<Int> = MutableLiveData(-1)
    private var _topCard: MutableLiveData<Card> = MutableLiveData(Card("", "", 0))
    private var _event: MutableLiveData<String> = MutableLiveData("")

    //the other non readable values
    private var deck: MutableList<Card> = mutableListOf()
    private var playerNumber = 0
    private var players: ArrayList<Player> = ArrayList()
    private var pile: ArrayList<Card> = ArrayList()
    private var skip: Boolean = false
    private var draw2: Boolean = false
    private var order: Int = 1

    //readable values
    var playerOneDeck: MutableLiveData<List<Card>> = _playerOneDeck
    var player2Count: MutableLiveData<Int> = _player2Count
    var player3Count: MutableLiveData<Int> = _player3Count
    var player4Count: MutableLiveData<Int> = _player4Count
    var winner: MutableLiveData<Int> = _winner
    var topCard: MutableLiveData<Card> = _topCard
    var event: MutableLiveData<String> = _event


    //init function that sets up the players the deck and the pile when the game view model is first created
    init {
        //creates deck
        createDeck()
        refillDeck()

        //sets the pile and top card
        pile.add(deck[deck.size - 1])
        deck.removeAt(deck.size - 1)
        _topCard.value = pile[0]

        //calls function that creates players
        playerNumber = 4
        createPlayers()
    }//end of init function

    //function that fills list with all the players
    private fun createPlayers() {
        var player: Player
        for (i in 1..playerNumber) {
            player = Player(i, deck)
            player.create()
            players.add(player)

            //sets each of the player counts
            if (i == 2)
                _player2Count.value = players[i - 1].getDeck().size
            if (i == 3)
                _player3Count.value = players[i - 1].getDeck().size
            if (i == 4)
                _player4Count.value = players[i - 1].getDeck().size
        }//end of for-loop
        _playerOneDeck.value = players[0].getDeck()
    }//end of createPlayers

    //fills pile with all cards involved in game
    private fun createDeck() {
        var card = Card("blue", "0", R.drawable.b0)
        pile.add(card)
        card = Card("blue", "1", R.drawable.b1)
        pile.add(card)
        card = Card("blue", "2", R.drawable.b2)
        pile.add(card)
        card = Card("blue", "3", R.drawable.b3)
        pile.add(card)
        card = Card("blue", "4", R.drawable.b4)
        pile.add(card)
        card = Card("blue", "5", R.drawable.b5)
        pile.add(card)
        card = Card("blue", "6", R.drawable.b6)
        pile.add(card)
        card = Card("blue", "7", R.drawable.b7)
        pile.add(card)
        card = Card("blue", "8", R.drawable.b8)
        pile.add(card)
        card = Card("blue", "9", R.drawable.b9)
        pile.add(card)
        card = Card("blue", "reverse", R.drawable.breverse)
        pile.add(card)
        card = Card("blue", "skip", R.drawable.bstop)
        pile.add(card)
        card = Card("blue", "draw2", R.drawable.bdraw2)
        pile.add(card)

        card = Card("red", "0", R.drawable.r0)
        pile.add(card)
        card = Card("red", "1", R.drawable.r1)
        pile.add(card)
        card = Card("red", "2", R.drawable.r2)
        pile.add(card)
        card = Card("red", "3", R.drawable.r3)
        pile.add(card)
        card = Card("red", "4", R.drawable.r4)
        pile.add(card)
        card = Card("red", "5", R.drawable.r5)
        pile.add(card)
        card = Card("red", "6", R.drawable.r6)
        pile.add(card)
        card = Card("red", "7", R.drawable.r7)
        pile.add(card)
        card = Card("red", "8", R.drawable.r8)
        pile.add(card)
        card = Card("red", "9", R.drawable.r9)
        pile.add(card)
        card = Card("red", "reverse", R.drawable.rreverse)
        pile.add(card)
        card = Card("red", "skip", R.drawable.rstop)
        pile.add(card)
        card = Card("red", "draw2", R.drawable.rdraw2)
        pile.add(card)

        card = Card("yellow", "0", R.drawable.y0)
        pile.add(card)
        card = Card("yellow", "1", R.drawable.y1)
        pile.add(card)
        card = Card("yellow", "2", R.drawable.y2)
        pile.add(card)
        card = Card("yellow", "3", R.drawable.y3)
        pile.add(card)
        card = Card("yellow", "4", R.drawable.y4)
        pile.add(card)
        card = Card("yellow", "5", R.drawable.y5)
        pile.add(card)
        card = Card("yellow", "6", R.drawable.y6)
        pile.add(card)
        card = Card("yellow", "7", R.drawable.y7)
        pile.add(card)
        card = Card("yellow", "8", R.drawable.y8)
        pile.add(card)
        card = Card("yellow", "9", R.drawable.y9)
        pile.add(card)
        card = Card("yellow", "reverse", R.drawable.yreverse)
        pile.add(card)
        card = Card("yellow", "skip", R.drawable.ystop)
        pile.add(card)
        card = Card("yellow", "draw2", R.drawable.ydraw2)
        pile.add(card)

        card = Card("green", "0", R.drawable.g0)
        pile.add(card)
        card = Card("green", "1", R.drawable.g1)
        pile.add(card)
        card = Card("green", "2", R.drawable.g2)
        pile.add(card)
        card = Card("green", "3", R.drawable.g3)
        pile.add(card)
        card = Card("green", "4", R.drawable.g4)
        pile.add(card)
        card = Card("green", "5", R.drawable.g5)
        pile.add(card)
        card = Card("green", "6", R.drawable.g6)
        pile.add(card)
        card = Card("green", "7", R.drawable.g7)
        pile.add(card)
        card = Card("green", "8", R.drawable.g8)
        pile.add(card)
        card = Card("green", "9", R.drawable.g9)
        pile.add(card)
        card = Card("green", "reverse", R.drawable.greverse)
        pile.add(card)
        card = Card("green", "skip", R.drawable.gstop)
        pile.add(card)
        card = Card("green", "draw2", R.drawable.gdraw2)
        pile.add(card)

        card = Card("blue", "0", R.drawable.b0)
        pile.add(card)
        card = Card("blue", "1", R.drawable.b1)
        pile.add(card)
        card = Card("blue", "2", R.drawable.b2)
        pile.add(card)
        card = Card("blue", "3", R.drawable.b3)
        pile.add(card)
        card = Card("blue", "4", R.drawable.b4)
        pile.add(card)
        card = Card("blue", "5", R.drawable.b5)
        pile.add(card)
        card = Card("blue", "6", R.drawable.b6)
        pile.add(card)
        card = Card("blue", "7", R.drawable.b7)
        pile.add(card)
        card = Card("blue", "8", R.drawable.b8)
        pile.add(card)
        card = Card("blue", "9", R.drawable.b9)
        pile.add(card)
        card = Card("blue", "reverse", R.drawable.breverse)
        pile.add(card)
        card = Card("blue", "skip", R.drawable.bstop)
        pile.add(card)
        card = Card("blue", "draw2", R.drawable.bdraw2)
        pile.add(card)

        card = Card("red", "0", R.drawable.r0)
        pile.add(card)
        card = Card("red", "1", R.drawable.r1)
        pile.add(card)
        card = Card("red", "2", R.drawable.r2)
        pile.add(card)
        card = Card("red", "3", R.drawable.r3)
        pile.add(card)
        card = Card("red", "4", R.drawable.r4)
        pile.add(card)
        card = Card("red", "5", R.drawable.r5)
        pile.add(card)
        card = Card("red", "6", R.drawable.r6)
        pile.add(card)
        card = Card("red", "7", R.drawable.r7)
        pile.add(card)
        card = Card("red", "8", R.drawable.r8)
        pile.add(card)
        card = Card("red", "9", R.drawable.r9)
        pile.add(card)
        card = Card("red", "reverse", R.drawable.rreverse)
        pile.add(card)
        card = Card("red", "skip", R.drawable.rstop)
        pile.add(card)
        card = Card("red", "draw2", R.drawable.rdraw2)
        pile.add(card)

        card = Card("yellow", "0", R.drawable.y0)
        pile.add(card)
        card = Card("yellow", "1", R.drawable.y1)
        pile.add(card)
        card = Card("yellow", "2", R.drawable.y2)
        pile.add(card)
        card = Card("yellow", "3", R.drawable.y3)
        pile.add(card)
        card = Card("yellow", "4", R.drawable.y4)
        pile.add(card)
        card = Card("yellow", "5", R.drawable.y5)
        pile.add(card)
        card = Card("yellow", "6", R.drawable.y6)
        pile.add(card)
        card = Card("yellow", "7", R.drawable.y7)
        pile.add(card)
        card = Card("yellow", "8", R.drawable.y8)
        pile.add(card)
        card = Card("yellow", "9", R.drawable.y9)
        pile.add(card)
        card = Card("yellow", "reverse", R.drawable.yreverse)
        pile.add(card)
        card = Card("yellow", "skip", R.drawable.ystop)
        pile.add(card)
        card = Card("yellow", "draw2", R.drawable.ydraw2)
        pile.add(card)

        card = Card("green", "0", R.drawable.g0)
        pile.add(card)
        card = Card("green", "1", R.drawable.g1)
        pile.add(card)
        card = Card("green", "2", R.drawable.g2)
        pile.add(card)
        card = Card("green", "3", R.drawable.g3)
        pile.add(card)
        card = Card("green", "4", R.drawable.g4)
        pile.add(card)
        card = Card("green", "5", R.drawable.g5)
        pile.add(card)
        card = Card("green", "6", R.drawable.g6)
        pile.add(card)
        card = Card("green", "7", R.drawable.g7)
        pile.add(card)
        card = Card("green", "8", R.drawable.g8)
        pile.add(card)
        card = Card("green", "9", R.drawable.g9)
        pile.add(card)
        card = Card("green", "reverse", R.drawable.greverse)
        pile.add(card)
        card = Card("green", "skip", R.drawable.gstop)
        pile.add(card)
        card = Card("green", "draw2", R.drawable.gdraw2)
        pile.add(card)
    }//end of create deck

    //refills deck with pile (called whenever deck runs out of cards
    private fun refillDeck() {
        var random: Int
        val length = pile.size
        for (i in 1..length) {
            random = (0 until pile.size).random()
            deck.add(pile[random])
            pile.removeAt(random)
        }
    }//end of refill deck

    //function that draws one card for player one
    fun draw() {
        //if statement checks if deck is empty
        if(deck.size == 0)
            refillDeck()

        //if statement checks if previous card is draw2 or skip
        if (!skip && !draw2) {
            _event.value = "You've drawn a card\n"
            val temp: Int = players[0].getDeck().size
            players[0].draw()

            //if statement checks if there are no more cards in the deck
            if (players[0].getDeck().size == temp)
                _event.value = "lose turn because of there wasn't enough cards\n"

        } else {
            //block of code ran when player has been skipped or forced to draw 2
            _event.value = "You've been skipped\n"

            //if draw2 is true then the current player draws 2 cards
            if (draw2) {
                _event.value = "You've been skipped and forced to draw 2 cards\n"
                players[0].draw()
                players[0].draw()
            }

            //changes draw2 and skip boolean variables back to false
            skip = false
            draw2 = false
        }//end of if-else statement that handles if the player has been skipped or forced to draw 2
        computer()
        _playerOneDeck = MutableLiveData(players[0].getDeck())
        event = _event
    }//end of draw

    //function that puts card in deck and takes it out player one's pile
    fun place(card: Card) {
        //if statement checks if previous card is draw2 or skip
        if (!skip && !draw2) {
            _event.value = ""
            _topCard.value?.let { players[0].setTop(it) }

            /* if playable deck contains the chosen card then the card is added to the pile
               If it is not in the playable deck then return to game
            */
            if (players[0].getPlayable().contains(card)) {
                //adds placed card to pile
                pile.add(card)
                players[0].getDeck().removeAt(players[0].getDeck().indexOf(card))
                _topCard.value = pile[pile.size - 1]
                _event.value = "You placed ${card.getColor()} ${card.getValue()}\n"

                //checks if placed card is skip, draw2, or reverse
                if (pile[pile.size - 1].getValue() == "skip")
                    skip = true
                if (pile[pile.size - 1].getValue() == "draw2")
                    draw2 = true
                if (pile[pile.size - 1].getValue() == "reverse") {
                    order = if (order == 1)  -1 else 1
                    _event.value = _event.value + "Order is reversed\n"
                }

            } else {
                //block of code ran when chosen card is not playable
                _event.value = "Not a playable card"
                event = _event
                return
            }//end of if-else block that handles if a card is playable

            //if players deck is empty than winner is equal to true and return to game
            if (playerOneDeck.value?.isEmpty() == true) {
                _winner = MutableLiveData(1)
                winner = _winner
                return
            }//end of winner if statement

        } else {
            //block of code ran when player has been skipped or forced to draw 2
            _event.value = "You've been skipped\n"

            //if draw2 is true then the current player draws 2 cards
            if (draw2) {
                _event.value = "You've been skipped and forced to draw 2 cards\n"
                players[0].draw()
                players[0].draw()
            }

            //changes draw2 and skip boolean variables back to false
            skip = false
            draw2 = false
        }//end of if-else statement that handles if the player has been skipped or forced to draw 2

        //calls function that runs all the computers' turns
        computer()

        event = _event

    }//end of place

    //Runs the computers turns with random number
    private fun computer() {
        /*if order equals one that means that the game is running in normal order
        but if order is -1 than that means it is reverse order
         */
        var num = if (order == 1)  2 else playerNumber
        //while loop goes through each one of the computer players.
        while (if (order == 1) num <= playerNumber else num>1) {
            if(deck.size == 0)
                refillDeck()
            val player = players[num - 1]
            //if statement checks if previous player put down a draw 2 or skip
            if (!skip && !draw2) {
                _topCard.value?.let { player.setTop(it) }
                val pCards = player.getPlayable()
                val cards = player.getDeck()
                var random: Int

                if (pCards.size == 0) {
                    player.draw()
                    _event.value = _event.value + "Player $num drew a card\n"
                } else {
                    random = (0 until pCards.size).random()
                    pile.add(pCards[random])
                    cards.remove(pCards[random])
                    _topCard.value = pile[pile.size - 1]
                    _event.value =
                        _event.value + "Player $num placed " + _topCard.value!!.getColor() + " " + _topCard.value!!.getValue() + "\n"
                    if (pile[pile.size - 1].getValue() == "skip")
                        skip = true
                    if (pile[pile.size - 1].getValue() == "draw2")
                        draw2 = true
                    if (pile[pile.size - 1].getValue() == "reverse") {
                        order = if (order == 1)  -1 else 1
                        _event.value = _event.value + "Order is reversed\n"
                    }

                    //if a player has no more cards then winner variable is equal to true and you return to the game composable
                    if (player.getDeck().size == 0) {
                        _winner = MutableLiveData(num)
                        winner = _winner
                        return
                    }

                }//end of else(read if computer has a playable card)

            } else {
                //block of code ran when player has been skipped or forced to draw 2
                if (skip)
                    _event.value = _event.value + "Player $num has been skipped\n"
                //if draw2 is true then the current player draws 2 cards
                if (draw2) {
                    _event.value =
                        _event.value + "Player $num has been skipped and forced to draw 2 cards\n"
                    player.draw()
                    player.draw()
                }
                //changes draw2 and skip boolean variables back to false
                skip = false
                draw2 = false
            }//end of else statement(read when player is skipped)

            //statement that iterates num to whoever the next the player is
            num += order
        }//end of while loop

        _player2Count = MutableLiveData(players[1].getDeck().size)
        _player3Count = MutableLiveData(players[2].getDeck().size)
        _player4Count = MutableLiveData(players[3].getDeck().size)
        _playerOneDeck = MutableLiveData(players[0].getDeck())
        _topCard = topCard

        player2Count = _player2Count
        player3Count = _player3Count
        player4Count = _player4Count
        playerOneDeck = _playerOneDeck
    } //end of computer

    //function that takes in an Integer and sets it equal to numberOfPlayers
    fun setNumberOfPlayers(num: Int) {
        playerNumber = num
    }// end of setNumberOfPlayers()

    //function that resets game when called
    fun clearGame() {
        //non readable values
        _playerOneDeck = MutableLiveData(listOf())
        _player2Count = MutableLiveData(0)
        _player3Count = MutableLiveData(0)
        _player4Count = MutableLiveData(0)
        _winner = MutableLiveData(-1)
        _topCard = MutableLiveData(Card("", "", 0))
        _event = MutableLiveData("")

        //other non readable values
        deck = mutableListOf()
        playerNumber = 0
        players = ArrayList()
        pile = ArrayList()
        skip = false
        draw2 = false
        order = 1

        //readable values
        playerOneDeck = _playerOneDeck
        player2Count = _player2Count
        player3Count = _player3Count
        player4Count = _player4Count
        winner = _winner
        topCard = _topCard
        event = _event

        //init function
        createDeck()
        refillDeck()
        pile.add(deck[deck.size - 1])
        deck.removeAt(deck.size - 1)
        _topCard.value = pile[0]
        playerNumber = 4
        createPlayers()
    }//end of clearGame()

}//end of composable class