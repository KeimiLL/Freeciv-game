package com.mobilne.civ2077.ui.board.views.turn

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mobilne.civ2077.data.game.GameRepository
import com.mobilne.civ2077.data.game.Player
import com.mobilne.civ2077.data.game.Turn

class TurnViewModel(val turn: Turn , val id: Int, private var gameRepository: GameRepository) :
    ViewModel() {
    /*Todo sparwzdanie booleanów w bazie, kto zakończył turę, a kto nie
    *  dodanie propów dla innych userów z bazy i przklejenie ich do widoku*/

    private val _endOfTurn = "End your turn"
    private val _alreadyPassed = "Wait for others.."

    private val _otherPlayerDuringTurn = "Still playing.."
    private val _otherPlayerEndedTurn = "End of turn"


    //Todo funkcja czytająca ich stan i dająca dobre dane
    var user1State by mutableStateOf(_otherPlayerDuringTurn)
    var user2State by mutableStateOf(_otherPlayerDuringTurn)
    var user3State by mutableStateOf(_otherPlayerDuringTurn)

    var user1 by mutableStateOf("User 1: ")
    var user2 by mutableStateOf("User 2: ")
    var user3 by mutableStateOf("User 3: ")


    var buttonText by mutableStateOf(_endOfTurn)
    var duringTurn by mutableStateOf(true) /* Todo Czytanie z bazy zalogowanego usera*/


    fun pass() {
        if (duringTurn) {
            buttonText = _alreadyPassed
            duringTurn = false
            gameRepository.savePlayerEndOfTurn(id)
        }
    }

    /* Todo */
    fun usersState() {
        when (id) {
            1 -> {
                duringTurn = !turn.player1
                user1 = "You: "
            }
            2 -> {
                duringTurn = !turn.player2
                user2 = "You: "
            }
            3 -> {
                duringTurn = !turn.player3
                user3 = "You: "
            }
        }
        user1State = if (!turn.player1)
            _otherPlayerDuringTurn
        else
            _otherPlayerEndedTurn
        user2State = if (!turn.player2)
            _otherPlayerDuringTurn
        else
            _otherPlayerEndedTurn
        user3State = if (!turn.player3)
            _otherPlayerDuringTurn
        else
            _otherPlayerEndedTurn
    }

}