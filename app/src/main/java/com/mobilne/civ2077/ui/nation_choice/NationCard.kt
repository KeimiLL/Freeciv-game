package com.mobilne.civ2077.ui.nation_choice

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mobilne.civ2077.ui.board.BoardViewModel
import com.mobilne.civ2077.util.constants.Nations

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NationCard(
    nation: Nations,
    drawableId: Int,
    perk: String,
    nationChoiceViewModel: NationChoiceViewModel,
    boardViewModel: BoardViewModel,
) {
    Card(
        modifier = Modifier
            .height(230.dp)
            .width(IntrinsicSize.Min)
            .padding(5.dp),
        elevation = 8.dp,
        onClick = {
            nationChoiceViewModel.onNationChange(nation.nationName, drawableId)
            boardViewModel.onNationChange(nation.nationName)
        }
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .border(2.dp, nationChoiceViewModel.getBorderForNationCard(nation))
                .width(140.dp)
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
                        bottom = 16.dp
                    ),
            )
            Text(
                text = nation.nationName,
                style = MaterialTheme.typography.h5,
            )
            Text(
                modifier = Modifier.padding(8.dp),
                text = perk,
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Justify
            )
        }

    }
}