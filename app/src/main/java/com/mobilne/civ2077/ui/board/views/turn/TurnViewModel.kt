package com.mobilne.civ2077.ui.board.views.turn

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mobilne.civ2077.data.game.GameRepository
import com.mobilne.civ2077.data.game.Player
import com.mobilne.civ2077.data.game.Turn

class TurnViewModel(
    var turn: Turn, val id: Int,
    val player1: Player,
    val player2: Player,
    val player3: Player,
    private val gameRepository: GameRepository
) :
    ViewModel() {
    /*Todo sparwzdanie booleanów w bazie, kto zakończył turę, a kto nie
    *  dodanie propów dla innych userów z bazy i przklejenie ich do widoku*/

    private val _endOfTurn = "End your turn"
    private val _alreadyPassed = "Wait for others.."

    private val _otherPlayerDuringTurn = "Still playing.."
    private val _otherPlayerEndedTurn = "End of turn"

    private val goldPerTurn = 100
    private val goldPerTurnForSpain = 200


    // war
    private var wasWar = false
    private var isPlayer1onWar = false
    private var isPlayer2onWar = false
    private var isPlayer3onWar = false


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

    private fun endOfTurnAction() {

        isWar()
        if (wasWar) {
            val warWinner = warResult()
        }
        goldForEndingTurn()

        gameRepository.changeTurnCounter(turn.number + 1)
        gameRepository.savePlayerStateOfTurn(1, false)
        gameRepository.savePlayerStateOfTurn(2, false)
        gameRepository.savePlayerStateOfTurn(3, false)
    }

    private fun isWar() {
        //ataki na bazy
        //player1
        if (player1.armyPosition.x == player2.basePosition.x && player1.armyPosition.y == player2.basePosition.y) {
            isPlayer1onWar = true
            isPlayer2onWar = true
        }
        if (player1.armyPosition.x == player3.basePosition.x && player1.armyPosition.y == player3.basePosition.y) {
            isPlayer1onWar = true
            isPlayer3onWar = true
        }
        //player2
        if (player2.armyPosition.x == player1.basePosition.x && player2.armyPosition.y == player1.basePosition.y) {
            isPlayer2onWar = true
            isPlayer1onWar = true
        }
        if (player2.armyPosition.x == player3.basePosition.x && player2.armyPosition.y == player3.basePosition.y) {
            isPlayer2onWar = true
            isPlayer3onWar = true
        }
        //player3
        if (player3.armyPosition.x == player1.basePosition.x && player3.armyPosition.y == player1.basePosition.y) {
            isPlayer3onWar = true
            isPlayer1onWar = true
        }
        if (player3.armyPosition.x == player2.basePosition.x && player3.armyPosition.y == player2.basePosition.y) {
            isPlayer3onWar = true
            isPlayer2onWar = true
        }
        //spotkanie wojsk
        //player1
        if (player1.armyPosition.x == player2.armyPosition.x && player1.armyPosition.y == player2.armyPosition.y) {
            isPlayer1onWar = true
            isPlayer2onWar = true
        }
        if (player1.armyPosition.x == player3.armyPosition.x && player1.armyPosition.y == player3.basePosition.y) {
            isPlayer1onWar = true
            isPlayer3onWar = true
        }
        //player2
        if (player2.armyPosition.x == player1.armyPosition.x && player2.armyPosition.y == player1.armyPosition.y) {
            isPlayer2onWar = true
            isPlayer1onWar = true
        }
        if (player2.armyPosition.x == player3.armyPosition.x && player2.armyPosition.y == player3.armyPosition.y) {
            isPlayer2onWar = true
            isPlayer3onWar = true
        }
        //player3
        if (player3.armyPosition.x == player1.armyPosition.x && player3.armyPosition.y == player1.armyPosition.y) {
            isPlayer3onWar = true
            isPlayer1onWar = true
        }
        if (player3.armyPosition.x == player2.armyPosition.x && player3.armyPosition.y == player2.armyPosition.y) {
            isPlayer3onWar = true
            isPlayer2onWar = true
        }

        wasWar = isPlayer1onWar || isPlayer3onWar || isPlayer2onWar

    }

    private fun calculateArmySize(player: Player): Int {
        var armySize = player.armySize
        var armySizeCounter = 1.0
        when (player.dev.right) {
            1 -> {
                armySizeCounter = 1.1
            }
            2 -> {
                armySizeCounter = 1.2
            }
            3 -> {
                armySizeCounter = 1.3
            }
            4 -> {
                armySizeCounter = 1.4
            }
        }
        if (player1.nation == "USA") {
            armySizeCounter += 0.1
        }
        armySize = (armySize * armySizeCounter).toInt()
        return armySize
    }


    private fun warResult() {
        var armyWithPerks1 = calculateArmySize(player1)
        var armyWithPerks2 = calculateArmySize(player2)
        var armyWithPerks3 = calculateArmySize(player3)

        if (isPlayer1onWar && isPlayer2onWar && isPlayer3onWar) {
            if (armyWithPerks1 > armyWithPerks2 && armyWithPerks1 > armyWithPerks3) {
                saveToWar(1, player1.armySize / 2, player2.gold/2+ player3.gold/2)
                saveToWar(2, player2.armySize / 2, -player2.gold/2)
                saveToWar(3, player3.armySize / 2, -player3.gold/2)
            } else if (armyWithPerks2 > armyWithPerks1 && armyWithPerks2 > armyWithPerks3) {
                saveToWar(1, player1.armySize / 2, -player1.gold/2)
                saveToWar(2, player2.armySize / 2, player1.gold/2 + player3.gold/2)
                saveToWar(3, player3.armySize / 2, -player3.gold/2)
            } else if (armyWithPerks3 > armyWithPerks1 && armyWithPerks3 > armyWithPerks2) {
                saveToWar(1, player1.armySize / 2, -player1.gold/2)
                saveToWar(2, player2.armySize / 2, -player2.gold/2)
                saveToWar(3, player3.armySize / 2, player1.gold/2 + player2.gold/2)
            } else {
                saveToWar(1, player1.armySize / 2, 0)
                saveToWar(2, player2.armySize / 2, 0)
                saveToWar(3, player3.armySize / 2, 0)
            }
        } else if (isPlayer1onWar && isPlayer2onWar) {
            if (armyWithPerks1 > armyWithPerks2) {
                saveToWar(1, player1.armySize / 2, player2.gold/2)
                saveToWar(2, player2.armySize / 2, -player2.gold/2)
            } else if (armyWithPerks2 > armyWithPerks1) {
                saveToWar(1, player1.armySize / 2, -player1.gold/2)
                saveToWar(2, player2.armySize / 2, player1.gold/2)
            } else {
                saveToWar(1, player1.armySize / 2, 0)
                saveToWar(2, player2.armySize / 2, 0)
            }
        } else if (isPlayer1onWar && isPlayer3onWar) {
            if (armyWithPerks1 > armyWithPerks3) {
                saveToWar(1, player1.armySize / 2, player3.gold/2)
                saveToWar(3, player3.armySize / 2, -player3.gold/2)
            } else if (armyWithPerks3 > armyWithPerks1) {
                saveToWar(1, player1.armySize / 2, -player1.gold/2)
                saveToWar(3, player3.armySize / 2, player1.gold/2)
            } else {
                saveToWar(1, player1.armySize / 2, 0)
                saveToWar(3, player3.armySize / 2, 0)
            }
        } else if (isPlayer2onWar && isPlayer3onWar) {
            if (armyWithPerks2 > armyWithPerks3) {
                saveToWar(2, player2.armySize / 2, player3.gold)
                saveToWar(3, player3.armySize / 2, -player3.gold)
            } else if (armyWithPerks3 > armyWithPerks2) {
                saveToWar(2, player2.armySize / 2, -player2.gold)
                saveToWar(3, player3.armySize / 2, player2.gold)
            } else {
                saveToWar(2, player2.armySize / 2, 0)
                saveToWar(3, player3.armySize / 2, 0)
            }
        }
    }


    private fun saveToWar(id: Int, armyChange: Int, goldChange: Int) {

    }

    private fun goldForEndingTurn() {
        var player1gold = goldPerTurn
        var player2gold = goldPerTurn
        var player3gold = goldPerTurn

        if (player1.nation == "Spain")
            player1gold = goldPerTurnForSpain
        if (player2.nation == "Spain")
            player2gold = goldPerTurnForSpain
        if (player3.nation == "Spain")
            player3gold = goldPerTurnForSpain
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