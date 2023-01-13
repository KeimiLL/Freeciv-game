package com.mobilne.civ2077.ui.nation_choice

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.mobilne.civ2077.R
import com.mobilne.civ2077.util.constants.Nations
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NationChoiceViewModel @Inject constructor() : ViewModel() {
    // perks
    val francePerk: String = "Your army has five extra steps each turn"
    val spainPerk: String = "Each turn gain additional 100 gold"
    val ukPerk: String = "Each perk costs 10% less"
    val usaPerk: String = "Your army deal 10% more damage"
    // variables

    // state
    var currentNation: String by mutableStateOf("Spain")
    var drawableId: Int by mutableStateOf(R.drawable.spain)

    // events
    fun onNationChange(newNation: String, id: Int) {
        currentNation = newNation
        drawableId = id
    }

    fun getBorderForNationCard(nation: Nations): Color {
        return if (nation.nationName === currentNation) Color.Blue else Color.White
    }
}