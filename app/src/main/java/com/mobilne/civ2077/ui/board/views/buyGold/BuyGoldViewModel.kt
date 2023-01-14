package com.mobilne.civ2077.ui.board.views.buyGold

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class BuyGoldViewModel : ViewModel() {

    //variables
    private val rate = 2
    private val maxGold = 1000000

    //state
    var goldToBuy by mutableStateOf("")
    var euroToPay by mutableStateOf("0")

    // events
    fun onTextChanged(newString: String) {
        try {
            if (newString.isNotEmpty()) {
                val result = newString.filter { it.isDigit() }
                if (result.toInt() < maxGold) {
                    goldToBuy = result
                    euroToPay = (goldToBuy.toInt() * rate).toString()
                } else {
                    goldToBuy = maxGold.toString()
                    euroToPay = (maxGold * 2).toString()
                }
            } else {
                goldToBuy = ""
                euroToPay = "0"
            }
        } catch (e: Exception) {
            println(e)
        }

    }


}

