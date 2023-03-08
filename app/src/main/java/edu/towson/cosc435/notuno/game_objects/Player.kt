package edu.towson.cosc435.notuno.game_objects


//Class to create Player object
class Player(x: Int, y: MutableList<Card>) {
    private var num: Int = x
    private var deck2: MutableList<Card> = y
    private var top: Card = Card("L", "L", 0)

    //list of cards containing the player hand
    private val cards: MutableList<Card> = mutableListOf()

    //list of cards containing playable cards from the player's hand
    private var playableCards: MutableList<Card> =
        cards.filter { card -> (card.getValue() == top.getValue()) || (card.getColor() == top.getColor()) || (card.getColor() == "none") || (top.getColor() == "none") } as MutableList<Card>

    //function that sets the top variable equal to the variable passed to it
    @JvmName("setTop1")
    fun setTop(x: Card) {
        top = x
    }//end of setTop

    //function that creates deck for player
    fun create() {
        num = 0
        for (i in 1..7) {
            draw()
        }
    }//end of create

    //function that returns hand of player
    fun getDeck(): MutableList<Card> {
        return cards
    }//end of getDeck

    //function that returns playable deck
    fun getPlayable(): MutableList<Card> {
        playableCards = cards.filter { card ->
            (card.getValue() == top.getValue()) || (card.getColor() == top.getColor()) || (card.getColor() == "none") || (top.getColor() == "none")
        } as MutableList<Card>
        return playableCards
    }//end of getPlayable

    //function that adds a random card to the players hand from the deck
    fun draw() {
        try {
            val random: Int = (0 until deck2.size).random()
            cards.add(deck2[random])
            deck2.removeAt(random)
        } catch (_: Throwable) {

        }
    }//end of draw function
}//end of player class