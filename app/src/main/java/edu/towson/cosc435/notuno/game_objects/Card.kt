package edu.towson.cosc435.notuno.game_objects

//Class to create Card object
class Card(x: String, y: String, z: Int) {
    var color: String = x
    private var value: String = y
    private var img: Int = z

    //return color of card
    @JvmName("getColor1")
    fun getColor(): String {
        return color
    }

    //returns value of card
    @JvmName("getValue1")
    fun getValue(): String {
        return value
    }

    //returns image integer of card for painter Resource
    fun getImage(): Int {
        return img
    }
}//end of Card class