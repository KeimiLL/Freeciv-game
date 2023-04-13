package com.mobilne.civ2077.ui.board

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mobilne.civ2077.R
import com.mobilne.civ2077.navigation.ROUTE_HOME
import com.mobilne.civ2077.ui.board.views.army.Army
import com.mobilne.civ2077.ui.board.views.army.ArmyViewModel
import com.mobilne.civ2077.ui.board.views.buyGold.BuyGold
import com.mobilne.civ2077.ui.board.views.buyGold.BuyGoldViewModel
import com.mobilne.civ2077.ui.board.views.endOfTurn.EndOfTurn
import com.mobilne.civ2077.ui.board.views.tree.Tree
import com.mobilne.civ2077.ui.board.views.tree.TreeViewModel
import com.mobilne.civ2077.ui.board.views.turn.Turn
import com.mobilne.civ2077.ui.board.views.turn.TurnViewModel

@Composable
fun FullBoardView(
    boardViewModel: BoardViewModel,
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(5.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        navController.navigate(ROUTE_HOME) {
                        }
                    },
                    modifier = Modifier
                        .height(60.dp)
                        .width(130.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff004f88)),
                    contentPadding = PaddingValues(12.dp),
                    shape = RoundedCornerShape(20.dp),
                    elevation = ButtonDefaults.elevation(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.home),
                        contentDescription = "home",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Home",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        color = Color(255, 255, 255),
                        fontWeight = FontWeight.Bold
                    )
                }

                ButtonItem(
                    "Gold: ${boardViewModel.getPlayerByIndex(boardViewModel.currentPlayerIndex.value).gold}",
                    boardViewModel
                )

                ButtonItem("Turn: ${boardViewModel.turn.value.number}", boardViewModel)

                ButtonItem("War Log", boardViewModel)
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .weight(3.5f)
                    .padding(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                //boardViewModel.getPlayerByIndex(boardViewModel.currentPlayerIndex.toString().toInt()))
                if (boardViewModel.currentView.contains("Army"))
                    Army(
                        viewModel = ArmyViewModel(
                            player = boardViewModel.getPlayerByIndex(boardViewModel.currentPlayerIndex.value),
                            id = boardViewModel.currentPlayerIndex.value,
                            gameRepository = boardViewModel.gameRepository
                        )
                    )
                else if (boardViewModel.currentView.contains("Tech Tree"))
                    Tree(
                        viewModel = TreeViewModel(
                            player = boardViewModel.getPlayerByIndex(boardViewModel.currentPlayerIndex.value),
                            id = boardViewModel.currentPlayerIndex.value,
                            gameRepository = boardViewModel.gameRepository
                        )
                    )
                else if (boardViewModel.currentView.contains("Gold"))
                    BuyGold(viewModel = BuyGoldViewModel())
                else if (boardViewModel.currentView.contains("Turn"))
                    Turn(
                        viewModel = TurnViewModel(
                            id = boardViewModel.currentPlayerIndex.value,
                            player1 = boardViewModel.player1.value,
                            player2 = boardViewModel.player2.value,
                            player3 = boardViewModel.player3.value,
                            gameRepository = boardViewModel.gameRepository,
                            turn = boardViewModel.turn.value
                        )
                    )
                else if (boardViewModel.currentView.contains("War Log"))
                    EndOfTurn(boardViewModel = boardViewModel)
                else
                    Map()
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(5.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ButtonItem("Tech Tree", boardViewModel)

                ButtonXYItem(
                    "Army",
                    x = boardViewModel.getPlayerByIndex(boardViewModel.currentPlayerIndex.value).armyPosition.x,
                    y = boardViewModel.getPlayerByIndex(boardViewModel.currentPlayerIndex.value).armyPosition.y,
                    boardViewModel
                )

                ButtonXYItem(
                    "Map",
                    x = boardViewModel.getPlayerByIndex(boardViewModel.currentPlayerIndex.value).basePosition.x,
                    y = boardViewModel.getPlayerByIndex(boardViewModel.currentPlayerIndex.value).basePosition.y,
                    boardViewModel
                )
            }
        }
    }
}


@Composable
fun ButtonItem(
    txt: String = "Nazwa-przycisku",
    boardViewModel: BoardViewModel,
) {
    Button(
        modifier = Modifier
            .height(60.dp)
            .width(130.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff004f88)),
        contentPadding = PaddingValues(12.dp),
        onClick = { boardViewModel.changeView(txt) },
        shape = RoundedCornerShape(20.dp),
        elevation = ButtonDefaults.elevation(8.dp)
    ) {
        if (txt.contains("Gold", ignoreCase = true)) {
            Image(
                painter = painterResource(id = R.drawable.coin),
                contentDescription = "coin",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(20.dp)
            )
        } else if (txt.contains("Turn", ignoreCase = true)) {
            Image(
                painter = painterResource(id = R.drawable.phase),
                contentDescription = "phase",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(20.dp)
            )
        } else if (txt.contains("War Log", ignoreCase = true)) {
            Image(
                painter = painterResource(id = R.drawable.sum),
                contentDescription = "sum",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(20.dp)
            )
        } else if (txt.contains("Tech", ignoreCase = true)) {
            Image(
                painter = painterResource(id = R.drawable.tech),
                contentDescription = "tech",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(20.dp)
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = txt,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = Color(255, 255, 255),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ButtonXYItem(
    txt: String = "btnXY",
    x: Int = 0,
    y: Int = 0,
    boardViewModel: BoardViewModel
) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Bottom
    ) {
        Row {
            Button(
                modifier = Modifier
                    .height(60.dp)
                    .width(130.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff004f88)),
                contentPadding = PaddingValues(12.dp),
                onClick = { boardViewModel.changeView(txt) },
                shape = RoundedCornerShape(20.dp),
                elevation = ButtonDefaults.elevation(8.dp)
            ) {
                if (txt.contains("Army", ignoreCase = true)) {
                    Image(
                        painter = painterResource(id = R.drawable.soldier),
                        contentDescription = "soldier",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(20.dp)
                    )
                } else if (txt.contains("Map", ignoreCase = true)) {
                    Image(
                        painter = painterResource(id = R.drawable.map_icon),
                        contentDescription = "map_icon",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = txt,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    color = Color(255, 255, 255),
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .background(Color(0xff266330), shape = RoundedCornerShape(15.dp))
                .width(130.dp)
                .clip(shape = RoundedCornerShape(15.dp))
                .padding(5.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "X: $x, Y: $y",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    color = Color(255, 255, 255)
                )
                if (txt == "Army") {
                    Row(
                        modifier = Modifier
                            .background(Color(0xff266330))
                            .width(130.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Size:",
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center,
                            color = Color(255, 255, 255)
                        )
                        Text(
                            text = boardViewModel.getPlayerByIndex(boardViewModel.currentPlayerIndex.value).armySize.toString() + " k",
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center,
                            color = Color(255, 255, 255)
                        )
                    }

                }
            }

        }
    }
}