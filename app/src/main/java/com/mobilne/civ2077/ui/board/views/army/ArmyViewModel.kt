package com.mobilne.civ2077.ui.board.views.army

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mobilne.civ2077.data.game.Player

class ArmyViewModel(var player: Player) : ViewModel() {
//    Todo
//     przekazanie aktualnych koordynatów i ustalenie limitu chodzenia,
//     przekazanie wartości z bazy ile golda ma osoba żeby wyliczyć ile moze jednostek kupić,
//     zapisywanie do bazy kupionych jednostek,
//     zapisywanie nowych koordynatów wojska do bazy

    //variables
    private val maxUnits = 1000
    private val maxCoordinate = 10
    private val goldPerUnit = 10
    private val maxRange = 1

    //state
    var destinationX by mutableStateOf("")
    var destinationY by mutableStateOf("")
    var unitsCount by mutableStateOf("")
    var goldToPay by mutableStateOf("0")

    // units events
    fun onUnitsChanged(newString: String) {
        if (newString.isNotEmpty()) {
            val result = newString.filter { it.isDigit() }.toInt()
            if (result < maxUnits) {
                unitsCount = result.toString()
                goldToPay = (result * goldPerUnit).toString()
            } else {
                unitsCount = "100"
                goldToPay = "1000"
            }
        } else {
            unitsCount = ""
            goldToPay = "0"
        }

    }

    //destination events
    fun onXChange(newString: String) {
        if (newString.isNotEmpty()) {
            val result = newString.filter { it.isDigit() }.toInt()
            destinationX = if (result < maxCoordinate)
                result.toString()
            else {
                "10"
            }
        } else
            destinationX = ""
    }

    fun onYChange(newString: String) {
        if (newString.isNotEmpty()) {
            val result = newString.filter { it.isDigit() }.toInt()
            destinationY = if (result < maxCoordinate)
                result.toString()
            else {
                "10"
            }
        } else
            destinationY = ""
    }

    fun buy(){
        if (goldToPay.toInt()<= player.gold) {
            /*Todo zapis do bazy player z nowym goldem  który jest aktualny stan + units count*/
        }
    }

    fun send(){
        if(destinationX != "" && destinationY != "") {
            /*Todo zapis nowych coordynatów, destx i desty (sprawdzic czy )*/
        }
    }

}