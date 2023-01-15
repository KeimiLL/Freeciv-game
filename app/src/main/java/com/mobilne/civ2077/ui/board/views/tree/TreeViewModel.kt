package com.mobilne.civ2077.ui.board.views.tree

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import com.mobilne.civ2077.data.game.Dev
import com.mobilne.civ2077.data.game.GameRepository
import com.mobilne.civ2077.data.game.Player

class TreeViewModel(val player: Player, val id: Int, private var gameRepository: GameRepository) :
    ViewModel() {

    /*Todo czytanie z bazy, które perki są kupione i na tej podsatwie wyłączać przyciski kupione
    *  i te niemożliwe od kupienia i zostawić tylko te do kupienia
    *  Sprawdzanie czy jest doś hajsu na dane akcje
    * */

    //private
    //values
    private val _economyPerkDescription = "10 less gold for all other perks"
    val economyDescription get() = _economyPerkDescription
    private val _armyPerkDescription = "Army deals 10% more damage"
    val armyPerkDescription get() = _armyPerkDescription

    //variables
    private var _economyPerks = (player.dev.left) // czytanie z bazy
    val economyPerks get() = _economyPerks
    private var _armyPerks = (player.dev.right) //czytanie z bazy
    val armyPerks get() = _armyPerks

    //states
    //buttons booleans
    var buyButtonState by mutableStateOf(false)
    var armyPerksButtonsState by mutableStateOf(arrayOf(false, false, false, false))
    var economyPerksButtonsState by mutableStateOf(arrayOf(false, false, false, false))

    //variables states
    var currentPerk by mutableStateOf("")
    var goldToPay by mutableStateOf(100)

    fun changeForEconomyPerk() {
        buyButtonState = true
        currentPerk = _economyPerkDescription
    }

    fun changeForArmyPerk() {
        buyButtonState = true
        currentPerk = _armyPerkDescription
    }

    fun buy() {
        if (player.gold > goldToPay) {
            if (currentPerk == _armyPerkDescription) {
                _armyPerks++
                changeEconomyButtonsState()
            } else if (currentPerk == _economyPerkDescription) {
                _economyPerks++
                changeArmyButtonsState()
            }
            save(
                Player(
                    armyPosition = player.armyPosition,
                    armyPositionChanged = player.armyPositionChanged,
                    armySize = player.armySize,
                    basePosition = player.basePosition,
                    dev = Dev(left = _economyPerks, right = _armyPerks),
                    gold = player.gold - goldToPay,
                    nation = player.nation
                )
            )
            currentPerk = ""
            buyButtonState = false
            checkDevelopmentState()

        }
        //Todo zapis do bazy nowych perków
    }

    private fun changeEconomyButtonsState() {
        economyPerksButtonsState = arrayOf(false, false, false, false)
        for (i in 0..3) {
            if (_economyPerks == i) {
                economyPerksButtonsState[i] = true
            }
        }
    }

    private fun changeArmyButtonsState() {
        armyPerksButtonsState = arrayOf(false, false, false, false)
        for (i in 0..3) {
            if (_armyPerks == i) {
                armyPerksButtonsState[i] = true
            }
        }
    }

    private fun checkDevelopmentState() {
        if (_economyPerks == 4 && _armyPerks == 4) {
            buyButtonState = false
            goldToPay = 0
            currentPerk = "Development completed!"
        }
    }

    private fun changeGoldToPay() {
        goldToPay -= 10 * player.dev.left
    }

    fun initState() {
        _economyPerks = player.dev.left
        _armyPerks = player.dev.right
        goldToPay = if (player.nation == "UK") {
            90
        } else {
            100
        }
        changeGoldToPay()
        changeEconomyButtonsState()
        changeArmyButtonsState()
        checkDevelopmentState()
    }

    private fun save(player: Player) {
        gameRepository.savePlayer(id, player)
    }
}