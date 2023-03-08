Project: Not Uno

Project Description: 
    Not Uno is an application that allows the user to play a single player 
game of uno against computers. The theme of the app is inspired by Uno 
 and is composed of red, white and black. When the application is first started, you begin on the title 
screen with the Not Uno logo. The Not Uno logo is also the custom app icon. There's a bottom 
navigation tool that has three available options. Each of these options takes the app to a
different screen using Navigation.

    The first bottom navigation option says "Tutorial". When clicked it brings the user to the Tutorial page. This
composable page has mutliple composable rows containing a composable card explaining the rules and items of Not Uno.
At the bottom of the page is a card that allows you to click on a text that says "Click here for not Uno support"
When clicked your phone automatically goes to the phone app and puts in a number to call Not Uno Support. This is done
with an intent.

    The second bottom navigation option says "Single". When clicked, it brings you to the single player settings screen.
There's a TextField with a label that says "Choose Number of Players". The text field has a drop down menu that has three options.
The three options are 2, 3, and 4. Below the text field there is a grayed out button that says start. When one of the drop down menu
options are selected the button is able to be clicked. Whatever number you pick ia how many players will be in the
game including yourself. When you click the start button, a dialog box pops up and asks
"Are you sure you want to start a game of {Number of players picked} players?" There are two buttons
on the dialog that say "Start" and "Dont start". When you click the don't start button, the dialog 
disapears and goes back to the single player settings screen. But when you click the start 
button, the app takes you to the game screen and you're able to play the game.

    The game screen has a black box that represnts the game table. The table contains all the 
other players' hands. The hands are represented by the back of
an uno card. Next to each players' hand, there's a label saying "Player {num}" and another label 
that says "{number of cards in the computer hands} cards". At the bottom of the screen is 
the user's hand. It represented by a lazy row that contains every card in the player's hand. In the center
of the black table there are two cards. One card is revealed and the other card is hidden. The
revealed card represents the top of used pile. The hidden card represents the deck of cards where every 
player draws from. Under the cards representing the pile and deck, there will be white
colored text that pops up after the computers' turns saying what each player did during their turn.
For example,"
You placed red one
Player 2 placed blue 3
Player 3 placed green 3
Player 4 drew a card
"
When it is your turn you have two options. The first option is to click the card representing the deck.
This will cause you to draw a card from the deck and add it into your hand. The second option is to 
click one of the cards in your hand in order to place it. If the card does not have the same color or value 
as the top card than nothing will happen. If the card is a playable card than each of the computers' 
turns play out and are printed on the screen. The same thing happens after you draw a card.
When one of the players has no more cards, a dialog pops up saying that they won. When this dialog
is closed the app brings you back to the title screen. There is also a button on the top right 
of the black table that says "Exit". When clicked, the game is cleared and the player is brought back
to the title screen.

    The third bottom navigation option says "Online". When clicked, the player is brought to the
Player List Screen. This page shows a Lazy column with all of the Player's information on there.
The information includes their avatar image and their username. This information is recieved from
a json server and stored in a room database. The lazy column recieves the data from the database repository
and displays it.

    At any point, leaving the game will cause a notification to pop up trying to lure the user 
into opening the app again.

Technologies:
Bitbucket
Github + MyJsonServer
Android Studio

Third Party Libraries:

Activity Library
implementation 'androidx.activity:activity-compose:1.7.0-alpha02'

Appcombat Library
implementation 'androidx.appcompat:appcompat:1.5.1'

Coil Library
implementation "io.coil-kt:coil-compose:2.2.1"

Compose Library
implementation "androidx.compose.ui:ui:$compose_version"
implementation "androidx.compose.ui:ui-tooling:$compose_version"
implementation "androidx.compose.material:material:$compose_version"


Core Library
implementation 'androidx.core:core-ktx:1.9.0'

GSON Library
implementation 'com.google.code.gson:gson:2.9.1'

Lifecycle Library
implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.5.1'

Material Design Components Library
implementation 'com.google.android.material:material:1.7.0'

Navigation Compose Libray
implementation "androidx.navigation:navigation-compose:2.5.3"

OkHttp Library
implementation "com.squareup.okhttp3:okhttp:4.10.0"

Room Persistance Library
def room_version = "2.4.3"
    implementation "androidx.room:room-runtime:$room_version"
    implementation "androidx.room:room-ktx:$room_version"
    implementation "androidx.room:room-common:$room_version"
    annotationProcessor "androidx.room:room-compiler:$room_version"
    kapt "androidx.room:room-compiler:$room_version"
