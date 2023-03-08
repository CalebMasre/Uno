package edu.towson.cosc435.notuno

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import edu.towson.cosc435.notuno.game.Game
import edu.towson.cosc435.notuno.game.GameViewModel
import edu.towson.cosc435.notuno.nav.Screens
import edu.towson.cosc435.notuno.screens.*
import edu.towson.cosc435.notuno.database.DatabaseViewModel
import edu.towson.cosc435.notuno.notification.CreateNotification
import edu.towson.cosc435.notuno.ui.theme.NotunoTheme

@OptIn(ExperimentalFoundationApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //creates app variable to send to Main and tutorial Screen
        this.intent.extras
        val app = this
        setContent {
            NotunoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val nav = rememberNavController()
                    val scaffoldState = rememberScaffoldState()
                    rememberCoroutineScope()
                    Scaffold(
                        scaffoldState = scaffoldState,
                        topBar = {
                            TopAppBar(
                                title = { Text("Not Uno") }
                            )
                        },
                        bottomBar = {
                            val navBackStackEntry by nav.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination


                            BottomNavigation(
                                elevation = 25.dp
                            ) {
                                //Bottom Navigation option that brings you to tutorial Screen
                                BottomNavigationItem(
                                    selected = currentDestination?.route == Screens.Tut.route,
                                    onClick = {

                                        /* If statement in every bottom navigation item
                                            makes sure that you cannot leave the game
                                            screen with bottom navigation options
                                        */
                                        if (currentDestination?.route != Screens.Gam.route)
                                            nav.navigate(Screens.Tut.route)
                                    },
                                    icon = {
                                        Icon(Icons.Default.Info, "")
                                    },
                                    label = {
                                        Text("Tutorial")
                                    }
                                )//end of Bottom Navigation Item

                                //Bottom Navigation option that brings you to Single Player Settings Screen
                                BottomNavigationItem(
                                    selected = currentDestination?.route == Screens.Sin.route,
                                    onClick = {
                                        if (currentDestination?.route != Screens.Gam.route)
                                            nav.navigate(Screens.Sin.route)
                                    },
                                    icon = {
                                        Icon(Icons.Default.Face, "")
                                    },
                                    label = {
                                        Text("Single")
                                    }
                                )//end of Bottom Navigation Item

                                //Bottom Navigation option that brings you to Online Screen
                                BottomNavigationItem(
                                    selected = currentDestination?.route == Screens.Onl.route,
                                    onClick = {
                                        if (currentDestination?.route != Screens.Gam.route)
                                            nav.navigate(Screens.Onl.route) {
                                                popUpTo(Screens.Sin.route)
                                            }
                                    },
                                    icon = {
                                        Icon(Icons.Default.Share, "")
                                    },
                                    label = {
                                        Text("Online")
                                    }
                                )//end of Bottom Navigation Item
                            }//end of Bottom Navigation
                        },
                        backgroundColor = Color.DarkGray,
                        content = { padding ->
                            Main(nav, padding, app)
                        }
                    )

                }
            }
        }//end of set Content
    }//end of onCreate

    //function that creates notification when you leave the app
    @Override
    override fun onStop() {
        super.onStop()
        val context = applicationContext
        val createNotification = CreateNotification(
            context, "NotUno", "We already missed you." +
                    "\ncome back"
        )
        createNotification.showNotification()
    }//end of onStop

}//end of main activity class

//composable function that deals with navigating to all Screens of our program
@ExperimentalFoundationApi
@Composable
fun Main(nav: NavHostController, padding: PaddingValues, app: Context) {
    //initializing view model variables for the game and database
    val gameViewModel: GameViewModel = viewModel()
    val vm: DatabaseViewModel = viewModel()

    NavHost(
        navController = nav,
        startDestination = Screens.Tit.route
    ) {
        //navigates to the tutorial screen
        composable(Screens.Tut.route) {
            Tutorial(app)
        }
        //navigates to the single player settings screen
        composable(Screens.Sin.route) {
            SinglePlayer { page -> nav.navigate(page) }
        }
        //navigates to the database screen
        composable(Screens.Onl.route) {
            PlayerListScreen(vm = vm)
        }
        //navigates to the game screen
        composable(Screens.Gam.route) {
            Game(numberOfPlayers = playerNum, gameViewModel) { page -> nav.navigate(page) }
        }

        //navigates to the title screen
        composable(Screens.Tit.route) {
            TitleScreen()
        }
    }//end of NavHost
}//end of main class







