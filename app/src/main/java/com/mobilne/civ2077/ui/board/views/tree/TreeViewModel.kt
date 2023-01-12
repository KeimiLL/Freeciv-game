package com.mobilne.civ2077.ui.board.views.tree

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TreeViewModel: ViewModel() {

    /*Todo czytanie z bazy, które perki są kupione i na tej podsatwie wyłączać przyciski kupione
    *  i te niemożliwe od kupienia i zostawić tylko te do kupienia*/
    //trójstan
    private val _economyPerk = "10 less gold for all other perks"
    private val _armyPerk = "Army takes 10% more damage"



    var currentPerk by mutableStateOf("")
    var economyPerks by mutableStateOf(2)
    var armyPerks by mutableStateOf(0)
    var goldToPay by mutableStateOf(100)

    fun changeForEconomyPerk(){
        currentPerk = _economyPerk
    }

    fun changeForArmyPerk(){
        currentPerk = _armyPerk
    }

    fun buy(){

    }

    //Todo czytamy z bazy jakie umiejętności ma graczi obniżamy(gdzie jest zero, tyle razy obniżamy o 10)
    fun changeGoldToPay(){
        goldToPay = 100 - 10*economyPerks
    }

}