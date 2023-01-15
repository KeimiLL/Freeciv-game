package com.mobilne.civ2077.ui.board.views.tree

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TreeViewModel : ViewModel() {

    /*Todo czytanie z bazy, które perki są kupione i na tej podsatwie wyłączać przyciski kupione
    *  i te niemożliwe od kupienia i zostawić tylko te do kupienia
    *  Sprawdzanie czy jest doś hajsu na dane akcje
    * */
    //private
    //values
    private val _economyPerkDescription = "10 less gold for all other perks"
    val economyDescription get() = _economyPerkDescription
    private val _armyPerkDescription = "Army takes 10% more damage"
    val armyPerkDescription get() = _armyPerkDescription

    //variables
    private var _economyPerks by mutableStateOf(0) // czytanie z bazy
    val economyPerks get() = _economyPerks
    private var _armyPerks by mutableStateOf(0) //czytanie z bazy
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
        if (currentPerk == _armyPerkDescription) {
            _armyPerks++
            changeEconomyButtonsState()
        } else if (currentPerk == _economyPerkDescription) {
            _economyPerks++
            changeArmyButtonsState()
        }
        currentPerk = ""
        buyButtonState = false
        checkDevelopmentState()
        //Todo zapis do bazy nowych perków
    }

    fun changeEconomyButtonsState() {
        economyPerksButtonsState = arrayOf(false, false, false, false)
        for (i in 0..3) {
            if (_economyPerks == i) {
                economyPerksButtonsState[i] = true
            }
        }
    }

    fun changeArmyButtonsState() {
        armyPerksButtonsState = arrayOf(false, false, false, false)
        for (i in 0..3) {
            if (_armyPerks == i) {
                armyPerksButtonsState[i] = true
            }
        }
    }

    fun checkDevelopmentState() {
        if (_economyPerks == 4 && _armyPerks == 4) {
            buyButtonState = false
            currentPerk = "Development completed!"
        }
    }

    fun changeGoldToPay() {
        goldToPay = 100 - 10 * _economyPerks
    }

}