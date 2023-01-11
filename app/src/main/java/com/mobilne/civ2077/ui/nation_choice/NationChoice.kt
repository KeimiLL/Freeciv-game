package com.mobilne.civ2077.ui.nation_choice

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
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
fun NationChoice(viewModel: NationChoiceViewModel) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row {
            Text(
                text = "Choose your nation: ",
                style = MaterialTheme.typography.h3,
            )
            Image(
                painter = painterResource(id = viewModel.drawableId),
                contentDescription = "${viewModel.currentNation} flag",
                modifier = Modifier
                    .height(70.dp)
                    .padding(
                        top = 16.dp,
                        start = 8.dp,
                        end = 8.dp,
                        bottom = 8.dp
                    ),
            )
        }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            NationCard(
                nation = Nations.FRANCE,
                drawableId = R.drawable.france,
                perk = viewModel.francePerk,
                viewModel = viewModel
            )
            NationCard(
                nation = Nations.SPAIN,
                drawableId = R.drawable.spain,
                perk = viewModel.spainPerk,
                viewModel = viewModel
            )
            NationCard(
                nation = Nations.UNITED_KINGDOM,
                drawableId = R.drawable.uk,
                perk = viewModel.ukPerk,
                viewModel = viewModel
            )
            NationCard(
                nation = Nations.UNITED_STATES,
                drawableId = R.drawable.usa,
                perk = viewModel.usaPerk,
                viewModel = viewModel
            )
        }
        /* Todo button to submit*/
    }
}

@Preview
@Composable
fun PreviewNationChoice() {
    NationChoice(viewModel = NationChoiceViewModel())
}