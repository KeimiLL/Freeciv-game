package com.mobilne.civ2077.ui.board.views.turn

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TurnViewModel: ViewModel() {
    /*Todo sparwzdanie booleanów w bazie, kto zakończył turę, a kto nie
    *  dodanie propów dla innych userów z bazy i przklejenie ich do widoku*/

    private val _endOfTurn = "Zakończ Turę"
    private val _alreadyPassed = "Już zakończyłeś turę"

    private val _otherPlayerDuringTurn = "W trakcie tury"
    private val _otherPlayerEndedTurn = "Koniec tury"


    //Todo funkcja czytająca ich stan i dająca dobre dane
    var user1State by mutableStateOf(_otherPlayerDuringTurn)
    var user2State by mutableStateOf(_otherPlayerDuringTurn)
    var user3State by mutableStateOf(_otherPlayerDuringTurn)


    var buttonText by mutableStateOf(_endOfTurn)
    var duringTurn by mutableStateOf(true) /* Todo Czytanie z bazy zalogowanego usera*/



    fun pass(){
        if (duringTurn){
            buttonText = _alreadyPassed
            duringTurn = false
        }
    }

    /* Todo */
    fun usersState(){

    }

}