package com.mobilne.civ2077.ui.nation_choice

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mobilne.civ2077.R
import com.mobilne.civ2077.navigation.ROUTE_BOARD
import com.mobilne.civ2077.navigation.ROUTE_NATIONS
import com.mobilne.civ2077.ui.board.BoardViewModel
import com.mobilne.civ2077.util.constants.Nations

@Composable
fun NationChoice(
    nationChoiceViewModel: NationChoiceViewModel,
    boardViewModel: BoardViewModel,
    navController: NavHostController,
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxHeight()
    ) {
        Row {
            Text(
                text = "Choose your nation: ",
                style = MaterialTheme.typography.h3,
            )
            Image(
                painter = painterResource(id = nationChoiceViewModel.drawableId),
                contentDescription = "${nationChoiceViewModel.currentNation} flag",
                modifier = Modifier
                    .height(70.dp)
                    .padding(
                        top = 16.dp, start = 8.dp, end = 8.dp, bottom = 8.dp
                    ),
            )
        }

        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            NationCard(
                nation = Nations.FRANCE,
                drawableId = R.drawable.france,
                perk = nationChoiceViewModel.francePerk,
                nationChoiceViewModel = nationChoiceViewModel,
                boardViewModel = boardViewModel
            )
            NationCard(
                nation = Nations.SPAIN,
                drawableId = R.drawable.spain,
                perk = nationChoiceViewModel.spainPerk,
                nationChoiceViewModel = nationChoiceViewModel,
                boardViewModel = boardViewModel
            )
            NationCard(
                nation = Nations.UNITED_KINGDOM,
                drawableId = R.drawable.uk,
                perk = nationChoiceViewModel.ukPerk,
                nationChoiceViewModel = nationChoiceViewModel,
                boardViewModel = boardViewModel
            )
            NationCard(
                nation = Nations.UNITED_STATES,
                drawableId = R.drawable.usa,
                perk = nationChoiceViewModel.usaPerk,
                nationChoiceViewModel = nationChoiceViewModel,
                boardViewModel = boardViewModel
            )
        }
        Row {
            Button(
                modifier = Modifier
                    .fillMaxWidth(0.2f)
                    .padding(8.dp),
                onClick = {
                    boardViewModel.savePlayerNationChoice()
                    navController.navigate(ROUTE_BOARD) {
                        popUpTo(ROUTE_NATIONS) { inclusive = true }
                    }
                },

                ) {
                Text(
                    text = "Submit",
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}
