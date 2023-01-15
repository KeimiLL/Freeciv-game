package com.mobilne.civ2077.ui.board

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobilne.civ2077.ui.board.views.buyGold.BuyGold
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mobilne.civ2077.navigation.ROUTE_HOME
import com.mobilne.civ2077.navigation.ROUTE_LOGIN
import com.mobilne.civ2077.ui.auth.AuthViewModel
import com.mobilne.civ2077.ui.board.views.army.Army
import com.mobilne.civ2077.ui.board.views.buyGold.BuyGoldViewModel
import com.mobilne.civ2077.ui.board.views.army.ArmyViewModel
import com.mobilne.civ2077.ui.board.views.tree.Tree
import com.mobilne.civ2077.ui.board.views.tree.TreeViewModel
import com.mobilne.civ2077.ui.board.views.turn.Turn
import com.mobilne.civ2077.ui.board.views.turn.TurnViewModel

@Composable
fun FullBoardView(
    authViewModel: AuthViewModel,
    boardViewModel: BoardViewModel,
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .background(Color(0xFFc5ddf6))
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(5.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Firebase DB test:
//                ButtonItem(viewModel.currentView, viewModel)
                //Logout
                Button(
                    onClick = {
                        authViewModel.logout()
                        navController.navigate(ROUTE_LOGIN) {
                            popUpTo(ROUTE_HOME) {
                                inclusive = true
                            }
                        }
                    },
                    modifier = Modifier
                        .height(80.dp)
                        .width(120.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff004f88)),
                    contentPadding = PaddingValues(16.dp),
                ) {
                    Text(
                        text = "Logout",
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.Center,
                        color = Color(255, 255, 255)
                    )
                }

                ButtonItem(
                    "Gold: ${boardViewModel.getPlayerByIndex(boardViewModel.currentPlayerIndex.value).gold}",
                    boardViewModel
                )
                ButtonXYItem(
                    "Map",
                    x = boardViewModel.getPlayerByIndex(boardViewModel.currentPlayerIndex.value).basePosition.x,
                    y = boardViewModel.getPlayerByIndex(boardViewModel.currentPlayerIndex.value).basePosition.y,
                    boardViewModel
                )
                // Firebase DB testing:
//                ButtonItem(viewModel.gameState.value.waiting.toString())
//                ButtonItem(viewModel.currentTurnUid.value)
//                ButtonItem(boardViewModel.currentPlayerIndex.value.toString())
//                ButtonItem("Wyjście")
//                ButtonItem("Złoto")
//                ButtonXYItem("Zdj bazy", 55, 60)
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .weight(4f)
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
                    Turn(viewModel = TurnViewModel(
                        id = boardViewModel.currentPlayerIndex.value,
                        gameRepository = boardViewModel.gameRepository,
                        turn= boardViewModel.turn.value
                    ))
                else
                    Map()
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(5.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                ButtonItem("Turn: ${boardViewModel.turn.value.number}", boardViewModel)
                ButtonItem("Tech Tree", boardViewModel)
                ButtonXYItem(
                    "Army",
                    x = boardViewModel.getPlayerByIndex(boardViewModel.currentPlayerIndex.value).armyPosition.x,
                    y = boardViewModel.getPlayerByIndex(boardViewModel.currentPlayerIndex.value).armyPosition.y,
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
            .height(80.dp)
            .width(120.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff004f88)),
        contentPadding = PaddingValues(16.dp),
        onClick = { boardViewModel.changeView(txt) },
    ) {
        Text(
            text = txt,
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Center,
            color = Color(255, 255, 255)
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
                    .height(80.dp)
                    .width(120.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff004f88)),
                contentPadding = PaddingValues(16.dp),
                onClick = { boardViewModel.changeView(txt) },
            ) {
                Text(
                    text = txt,
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    color = Color(255, 255, 255)
                )
            }
        }

        Row(
            modifier = Modifier
                .background(Color(0xff266330))
                .width(120.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
                Text(
                    text = "X: $x, Y: $y",
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    color = Color(255, 255, 255)
                )
                if(txt == "Army"){
                    Row(modifier = Modifier
                        .background(Color(0xff266330))
                        .width(120.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Size:",
                            style = MaterialTheme.typography.body2,
                            textAlign = TextAlign.Center,
                            color = Color(255, 255, 255)
                        )
                        Text(
                            text = "73434 k",
                            style = MaterialTheme.typography.body2,
                            textAlign = TextAlign.Center,
                            color = Color(255, 255, 255)
                        )
                    }

                }
            }

        }
    }
}

@Preview
@Composable
fun PreviewFullBoardView() {
    FullBoardView(hiltViewModel(), hiltViewModel(), rememberNavController())
}