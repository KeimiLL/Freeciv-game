package com.mobilne.civ2077.ui.buyGoldDialog

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class BuyGoldDialogViewModel : ViewModel() {
    //variables
    val rate = 2
    //state
    var goldToBuy by mutableStateOf("")
    var euroToPay by mutableStateOf("0")

    // events
    fun onTextChanged(newString: String) {
        if (newString.isNotEmpty()){
            goldToBuy = newString
            euroToPay = (goldToBuy.toInt()*rate).toString()
        } else {
            euroToPay = "0"
            goldToBuy = ""
        }

    }




}

