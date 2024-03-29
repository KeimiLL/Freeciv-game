package com.mobilne.civ2077.ui.board.views.tree

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mobilne.civ2077.R

@Composable
fun Tree(viewModel: TreeViewModel) {
    viewModel.initState()
    Surface(shadowElevation = 4.dp, shape = RoundedCornerShape(16.dp)) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column(
                    modifier = Modifier
                        .weight(2.5f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.padding(vertical = 0.dp),
                            text = "Economy",
                            color = Color(0xff8887E3),
                            style = androidx.compose.material3.MaterialTheme.typography.titleLarge,
                        )
                        Spacer(modifier = Modifier.width(width = 10.dp))
                        Image(
                            painter = painterResource(id = R.drawable.economy),
                            contentDescription = "Economy",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(40.dp)
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
                        .weight(2.5f)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.padding(vertical = 0.dp),
                            text = "Army",
                            color = Color(0xff85ce37),
                            style = androidx.compose.material3.MaterialTheme.typography.titleLarge,
                        )
                        Spacer(modifier = Modifier.width(width = 10.dp))
                        Image(
                            painter = painterResource(id = R.drawable.army),
                            contentDescription = "Army",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(40.dp)
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
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffA9A8EB)),
        contentPadding = PaddingValues(12.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = ButtonDefaults.elevation(8.dp),
        enabled = viewModel.economyPerksButtonsState[id - 1],
        onClick = { viewModel.changeForEconomyPerk() },
    ) {
        if (id == 1) {
            Image(
                painter = painterResource(id = R.drawable.eco1),
                contentDescription = "eco1",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(55.dp)
            )
        } else if (id == 2) {
            Image(
                painter = painterResource(id = R.drawable.eco2),
                contentDescription = "eco2",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(55.dp)
            )
        } else if (id == 3) {
            Image(
                painter = painterResource(id = R.drawable.eco3),
                contentDescription = "eco3",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(55.dp)
            )
        } else if (id == 4) {
            Image(
                painter = painterResource(id = R.drawable.eco4),
                contentDescription = "eco4",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(55.dp)
            )
        }
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
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffbce491)),
        contentPadding = PaddingValues(4.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = ButtonDefaults.elevation(8.dp),
        enabled = viewModel.armyPerksButtonsState[id - 1],
        onClick = { viewModel.changeForArmyPerk() },
    ) {
        if (id == 1) {
            Image(
                painter = painterResource(id = R.drawable.army1),
                contentDescription = "army1",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(55.dp)
            )
        } else if (id == 2) {
            Image(
                painter = painterResource(id = R.drawable.army2),
                contentDescription = "army2",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(55.dp)
            )
        } else if (id == 3) {
            Image(
                painter = painterResource(id = R.drawable.army3),
                contentDescription = "army3",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(55.dp)
            )
        } else if (id == 4) {
            Image(
                painter = painterResource(id = R.drawable.army4),
                contentDescription = "army4",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(55.dp)
            )
        }
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
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffa9891c)),
            contentPadding = PaddingValues(12.dp),
            shape = RoundedCornerShape(20.dp),
            elevation = ButtonDefaults.elevation(8.dp),
            enabled = viewModel.buyButtonState,
            onClick = {
                viewModel.buy()
            },
        ) {
            Text(
                text = "Buy for " + viewModel.goldToPay,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
    }
}