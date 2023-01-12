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
    private val _economyPerk = "10 less gold for all other perks"
    private val _armyPerk = "Army takes 10% more damage"

    //variables
    private var _economyPerks by mutableStateOf(0) // czytanie z bazy
    private var _armyPerks by mutableStateOf(0) //czytanie z bazy

    //states
    //buttons booleans
    var buyButtonState by mutableStateOf(true)
    var armyPerksButtonsState by mutableStateOf(arrayOf(false, false, false, false))
    var economyPerksButtonsState by mutableStateOf(arrayOf(false, false, false, false))

    //variables states
    var currentPerk by mutableStateOf("")
    var goldToPay by mutableStateOf(100)

    fun changeForEconomyPerk() {
        currentPerk = _economyPerk
    }

    fun changeForArmyPerk() {
        currentPerk = _armyPerk
    }

    fun buy() {
        if (currentPerk != "") {
            if (currentPerk == _armyPerk) {
                _armyPerks++
            } else if (currentPerk == _economyPerk) {
                _economyPerks++
            }
            currentPerk = ""
            changeButtonsState()
            //Todo zapis do bazy nowych perków
        }
    }

    //Todo wywoływane po wczytaniu jak juz mamy na jakim miejscu rozwoju w drzewkach jest gracz z bazy
    //  rozdzielic funckje na army i economy
    fun changeButtonsState() {
        economyPerksButtonsState = arrayOf(false, false, false, false)
        armyPerksButtonsState = arrayOf(false, false, false, false)
        for (i in 0..3) {
            if (_economyPerks == i) {
                economyPerksButtonsState[i] = true
            }
            if (_armyPerks == i) {
                armyPerksButtonsState[i] = true
            }
        }
        if (_economyPerks == 4 && _armyPerks == 4) {
            currentPerk = "Development completed!"
            buyButtonState = false
        }

    }

    //Todo czytamy z bazy jakie umiejętności ma graczi obniżamy(gdzie jest zero, tyle razy obniżamy o 10)
    fun changeGoldToPay() {
        goldToPay = 100 - 10 * _economyPerks
    }

}