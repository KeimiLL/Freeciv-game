package com.mobilne.civ2077.ui.board.views.tree

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobilne.civ2077.ui.board.views.army.Army

@Composable
fun Tree(viewModel: TreeViewModel) {
    Box(
        modifier = Modifier
            .background(Color(0xFFfdebea))
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            viewModel.changeGoldToPay()
            Column(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 5.dp),
                        text = "Economy",
                        color = Color.Blue,
                        style = MaterialTheme.typography.h4,
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    EconomyItem(1, viewModel)
                    EconomyItem(2, viewModel)
                    EconomyItem(3, viewModel)
                    EconomyItem(4, viewModel)
                }
            }
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 5.dp),
                        text = "Army",
                        color = Color.Red,
                        style = MaterialTheme.typography.h4,
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ArmyItem(1, viewModel)
                    ArmyItem(2, viewModel)
                    ArmyItem(3, viewModel)
                    ArmyItem(4, viewModel)
                }
            }
            Row(
                modifier = Modifier
                    .weight(1f),
                verticalAlignment = Alignment.Bottom
            ) {
                InfoBar(viewModel)
            }
        }
    }
}

@Composable
fun EconomyItem(
    id: Int = 1,
    viewModel: TreeViewModel
) {
    Button(
        modifier = Modifier
            .height(80.dp)
            .width(80.dp),
        shape = RectangleShape,
        contentPadding = PaddingValues(16.dp),
        onClick = { viewModel.changeForEconomyPerk() },
    ) {
        Text(
            text = "lvl $id",
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Center,
            color = Color(255, 255, 255)
        )
    }
}

@Composable
fun ArmyItem(
    id: Int = 1,
    viewModel: TreeViewModel
) {
    Button(
        modifier = Modifier
            .height(80.dp)
            .width(80.dp),
        shape = RectangleShape,
        contentPadding = PaddingValues(16.dp),
        onClick = { viewModel.changeForArmyPerk() },
    ) {
        Text(
            text = "lvl $id",
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Center,
            color = Color(255, 255, 255)
        )
    }
}


@Composable
fun InfoBar(
    viewModel: TreeViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(7f),
            text = viewModel.currentPerk,
            textAlign = TextAlign.Center,
        )
        Button(
            modifier = Modifier
                .height(50.dp)
                .width(150.dp)
                .weight(3f),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff507d00)),
            shape = RectangleShape,
            contentPadding = PaddingValues(16.dp),

            onClick = { viewModel.buy() },
        ) {
            Text(
                text = "Buy for " + viewModel.goldToPay,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Center,
                color = Color(255, 255, 255)
            )
        }
    }
}

@Preview
@Composable
fun PreviewTree() {
    Tree(TreeViewModel())
}