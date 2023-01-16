package com.mobilne.civ2077.ui.board.views.turn

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mobilne.civ2077.data.game.GameRepository
import com.mobilne.civ2077.data.game.Player
import com.mobilne.civ2077.data.game.Turn

class TurnViewModel(var turn: Turn , val id: Int,
                    val player1: Player,
                    val player2: Player,
                    val player3: Player,
                    private val gameRepository: GameRepository) :
    ViewModel() {
    /*Todo sparwzdanie booleanów w bazie, kto zakończył turę, a kto nie
    *  dodanie propów dla innych userów z bazy i przklejenie ich do widoku*/

    private val _endOfTurn = "End your turn"
    private val _alreadyPassed = "Wait for others.."

    private val _otherPlayerDuringTurn = "Still playing.."
    private val _otherPlayerEndedTurn = "End of turn"

    private val goldPerTurn = 100
    private val goldPerTurnForSpain = 200
    private var wasWar = false


    //Todo funkcja czytająca ich stan i dająca dobre dane
    var user1State by mutableStateOf(_otherPlayerDuringTurn)
    var user2State by mutableStateOf(_otherPlayerDuringTurn)
    var user3State by mutableStateOf(_otherPlayerDuringTurn)

    var user1 by mutableStateOf("User 1: ")
    var user2 by mutableStateOf("User 2: ")
    var user3 by mutableStateOf("User 3: ")


    var buttonText by mutableStateOf(_endOfTurn)
    var duringTurn by mutableStateOf(true)


    fun pass() {
        if (duringTurn) {
            buttonText = _alreadyPassed
            duringTurn = false
            gameRepository.savePlayerStateOfTurn(id, true)
        }
        when (id) {
            1 -> {
                if (turn.player2 && turn.player3)
                    endOfTurnAction()
            }
            2 -> {
                if (turn.player1 && turn.player3)
                    endOfTurnAction()
            }
            3 -> {
                if (turn.player1 && turn.player2)
                    endOfTurnAction()
            }
        }
    }


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

    private fun endOfTurnAction(){
        if (player1.nation == "Spain")
            savePlayerGold(1, player1, goldPerTurnForSpain)
        else
            savePlayerGold(1, player1, goldPerTurn)
        if (player2.nation == "Spain")
            savePlayerGold(2, player2, goldPerTurnForSpain)
        else
            savePlayerGold(2, player2, goldPerTurn)
        if (player3.nation == "Spain")
            savePlayerGold(3, player3, goldPerTurnForSpain)
        else
            savePlayerGold(3, player3, goldPerTurn)

        gameRepository.changeTurnCounter(turn.number+1)
        gameRepository.savePlayerStateOfTurn(1, false)
        gameRepository.savePlayerStateOfTurn(2, false)
        gameRepository.savePlayerStateOfTurn(3, false)
    }

    private fun savePlayerGold(index: Int, player: Player, gold: Int) {
        val newPlayer = Player(
            armyPosition = player.armyPosition,
            armyPositionChanged = player.armyPositionChanged,
            armySize = player.armySize,
            basePosition = player.basePosition,
            dev = player.dev,
            gold = player.gold + gold,
            nation = player.nation
        )
        gameRepository.savePlayer(index, newPlayer)
    }

}