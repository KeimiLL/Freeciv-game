package com.mobilne.civ2077.ui.nation_choice

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobilne.civ2077.R
import com.mobilne.civ2077.util.constants.Nations

@Composable
fun NationChoice() {

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Choose your nation",
            style = MaterialTheme.typography.h3,
        )
        Row(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            NationCard(nation = Nations.FRANCE, drawableId = R.drawable.france, perk = "Your army has five extra steps each turn")
            NationCard(nation = Nations.SPAIN, drawableId = R.drawable.spain, perk = "Every turn gain additional 100 gold")
            NationCard(nation = Nations.UNITED_KINGDOM, drawableId = R.drawable.uk, perk= "Each perk costs 10% less")
            NationCard(nation = Nations.UNITED_STATES, drawableId = R.drawable.usa, perk = "Your army deal 10% more damage")
        }
    }
}

@Preview
@Composable
fun PreviewNationChoice() {
    NationChoice()
}