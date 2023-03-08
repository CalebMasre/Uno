package edu.towson.cosc435.notuno.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.towson.cosc435.notuno.dialog.ConfirmDialog
import edu.towson.cosc435.notuno.dialog.ConfirmViewModel
import edu.towson.cosc435.notuno.nav.Screens

var playerNum = 1

@Composable
fun SinglePlayer(navToGame: (String) -> Unit) {
    val confirmViewModel: ConfirmViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LocalContext.current.applicationContext
        var correctNumberOfPlayers by remember { mutableStateOf(false) }


        Row {
            Text(text = "Single Player Game", color = Color.White)
        }
        Row(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)) {

            var expanded by remember { mutableStateOf(false) }
            val suggestions = listOf(2, 3, 4)
            var selectedText by remember { mutableStateOf("") }

            var textfieldSize by remember { mutableStateOf(Size.Zero) }

            val icon = if (expanded)
                Icons.Filled.KeyboardArrowUp
            else
                Icons.Filled.KeyboardArrowDown

            OutlinedTextField(
                value = selectedText,
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        textfieldSize = coordinates.size.toSize()
                    },
                label = { Text("Choose Number of Players", color = Color.Black) },
                trailingIcon = {
                    Icon(icon, "contentDescription",
                        Modifier.clickable { expanded = !expanded })
                }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
            ) {
                suggestions.forEach { label ->
                    DropdownMenuItem(onClick = {
                        selectedText = "$label"
                        playerNum = label
                        expanded = false
                        correctNumberOfPlayers = true
                    }) {
                        Text(text = "$label")
                    }
                }
            }

        }

        ConfirmDialog(
            playerNum,
            confirmViewModel = confirmViewModel,
        )

        Row {
            Button(
                onClick = { confirmViewModel.openMessage { navToGame(Screens.Gam.route); } },
                enabled = correctNumberOfPlayers
            ) {
                Text(text = "Start")
            }
        }
    }
}