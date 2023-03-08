package edu.towson.cosc435.notuno.dialog

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ConfirmViewModel : ViewModel() {
    //non readable variables for Confirm View Model
    private var _open: MutableState<Boolean> = mutableStateOf(false)
    private var _do: (suspend () -> Unit)? = null

    //readable variables for Confirm View Model
    val open: State<Boolean> = _open

    //function that opens dialog box by setting the open variable equal to true
    fun openMessage(onConfirm: suspend () -> Unit) {
        _open.value = true
        _do = onConfirm
    }//end of openMessage


    //Runs stored function from SinglePlayerSettingsScreen that navigates you to game
    fun startGame() {
        if(_do != null) {
            viewModelScope.launch {
                //invokes function to go to Game composable
                _do!!.invoke()

                //closes dialog
                closeMessage()
            }//end of view model scope

        }//end of if statement

    }//end of startGame

    //function that closes dialog box
    fun closeMessage() {
        _open.value = false
    }//end of closeMessage

}//end of confirmViewModel