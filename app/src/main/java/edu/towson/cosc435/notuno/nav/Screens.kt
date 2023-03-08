package edu.towson.cosc435.notuno.nav


sealed class Screens(val route: String) {
    object Tut : Screens("tutorial")
    object Sin : Screens("single")
    object Onl : Screens("online")
    object Gam : Screens("game")
    object Tit : Screens("title")
}
