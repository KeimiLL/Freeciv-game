package com.mobilne.civ2077.ui.board.views.army

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mobilne.civ2077.data.game.ArmyPosition
import com.mobilne.civ2077.data.game.GameRepository
import com.mobilne.civ2077.data.game.Player


class ArmyViewModel(val player: Player, val id: Int, private var gameRepository: GameRepository) :
    ViewModel() {

    //variables
    private val maxUnits = 1001
    private val maxCoordinate = 10
    private val goldPerUnit = 10
    var maxRange = 1

    //state
    var destinationX by mutableStateOf("")
    var destinationY by mutableStateOf("")
    var unitsCount by mutableStateOf("")
    var goldToPay by mutableStateOf("0")

    // units events
    fun onUnitsChanged(newString: String) {
        try {
            if (newString.isNotEmpty()) {
                val result = newString.filter { it.isDigit() }.toInt()
                if (result < maxUnits) {
                    unitsCount = result.toString()
                    goldToPay = (result * goldPerUnit).toString()
                } else {
                    unitsCount = "1000"
                    goldToPay = "10000"
                }
            } else {
                unitsCount = ""
                goldToPay = "0"
            }
        } catch (e: Exception) {
            println(e)
        }
    }

    //destination events
    fun onXChange(newString: String) {
        try {
            destinationX = if (newString.isNotEmpty()) {
                val result = newString.filter { it.isDigit() }.toInt()
                if (result < maxCoordinate) result.toString()
                else {
                    "10"
                }
            } else ""
        } catch (e: Exception) {
            println(e)
        }
    }

    fun changeMaxRange() {
        if (player.nation == "France") maxRange = 3
    }


    fun onYChange(newString: String) {
        try {
            destinationY = if (newString.isNotEmpty()) {
                val result = newString.filter { it.isDigit() }.toInt()
                if (result < maxCoordinate) result.toString()
                else {
                    "10"
                }
            } else ""
        } catch (e: Exception) {
            println(e)
        }
    }

    fun buy() {
        if (goldToPay.toInt() <= player.gold) {
            save(
                Player(
                    armyPosition = player.armyPosition,
                    armyPositionChanged = player.armyPositionChanged,
                    armySize = player.armySize + unitsCount.toInt(),
                    basePosition = player.basePosition,
                    dev = player.dev,
                    gold = player.gold - goldToPay.toInt(),
                    nation = player.nation
                )
            )
        }
    }

    fun send() {
        if (destinationX != "" && destinationY != "" && !player.armyPositionChanged) {
            if (destinationX.toInt() - maxRange <= player.armyPosition.x && destinationX.toInt() + maxRange >= player.armyPosition.x && destinationY.toInt() - maxRange <= player.armyPosition.y && destinationY.toInt() + maxRange >= player.armyPosition.y) {
                save(
                    Player(
                        armyPosition = ArmyPosition(
                            x = destinationX.toInt(), y = destinationY.toInt()
                        ),
                        armyPositionChanged = true,
                        armySize = player.armySize,
                        basePosition = player.basePosition,
                        dev = player.dev,
                        gold = player.gold,
                        nation = player.nation
                    )
                )
            }
        }
    }

    private fun save(player: Player) {
        gameRepository.savePlayer(id, player)
    }

}