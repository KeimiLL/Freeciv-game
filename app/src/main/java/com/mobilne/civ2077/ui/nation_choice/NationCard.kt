package com.mobilne.civ2077.ui.nation_choice

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobilne.civ2077.R
import com.mobilne.civ2077.util.constants.Nations

@Composable
fun NationCard(nation: Nations, drawableId: Int, perk: String) {

    Card(
        modifier = Modifier
            .fillMaxHeight()
            .width(IntrinsicSize.Min)
            .padding(8.dp),
        elevation = 8.dp,
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(140.dp)
        ) {
            Image(
                painter = painterResource(id = drawableId),
                contentDescription = "$nation flag",
                modifier = Modifier
                    .height(80.dp)
                    .padding(
                        top = 16.dp,
                        start = 8.dp,
                        end = 8.dp,
                        bottom = 8.dp
                    ),
            )
            Text(
                text = nation.nationName,
                style = MaterialTheme.typography.h5,
            )
            Text(
                modifier = Modifier.padding(8.dp),
                text = perk,
                style = MaterialTheme.typography.h6,
            )
            Button(
                modifier = Modifier.fillMaxWidth(0.7f),
                onClick = { /*TODO*/ },
            ) {
                Text(
                    text = "Choose",
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                )
            }
        }

    }
}

@Preview
@Composable
fun PreviewNationCard() {
    NationCard(Nations.FRANCE, R.drawable.france, "Your army has five extra steps each turn")
}