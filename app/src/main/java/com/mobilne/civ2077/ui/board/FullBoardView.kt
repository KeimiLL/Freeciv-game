package com.mobilne.civ2077.ui.board

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
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
    authViewModel: AuthViewModel?,
    navController: NavHostController,
    viewModel: BoardViewModel = hiltViewModel()) {
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
                        authViewModel?.logout()
                        navController.navigate(ROUTE_LOGIN) {
                            popUpTo(ROUTE_HOME) {
                                inclusive = true
                            }
                        }
                    },
                    modifier = Modifier
                        .height(80.dp)
                        .width(120.dp),
                    shape = RectangleShape,
                    contentPadding = PaddingValues(16.dp),
                ) {
                Text(
                    text = "Logout",
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    color = Color(255, 255, 255)
                )
            }

                ButtonItem("Gold", viewModel)
                ButtonXYItem("Map", 10, 10, viewModel)
                // Firebase DB testing:
//                ButtonItem(viewModel.gameState.value.waiting.toString())
//                ButtonItem(viewModel.currentTurnUid.value)
//                ButtonItem(viewModel.players.value.uid1.toString())
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
                when (viewModel.currentView) {
                    "Army" -> {
                        Army(viewModel = ArmyViewModel())
                    }
                    "Tech Tree" -> {
                        Tree(viewModel = TreeViewModel()) /* Todo Tree viewmodel*/
                    }
                    "Gold" -> {
                        BuyGold(viewModel = BuyGoldViewModel())
                    }
                    "Turn" ->{
                        Turn(viewModel = TurnViewModel())
                    }
                    else -> {
                        Map()
                    }
                }
             }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(5.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                ButtonItem("Turn", viewModel)
                ButtonItem("Tech Tree", viewModel)
                ButtonXYItem("Army", 10, 10, viewModel)
            }
        }
    }
}


@Composable
fun ButtonItem(
    txt: String = "Nazwa-przycisku",
    viewModel: BoardViewModel,
) {
    Button(
        modifier = Modifier
            .height(80.dp)
            .width(120.dp),
        shape = RectangleShape,
        contentPadding = PaddingValues(16.dp),
        onClick = { viewModel.changeView(txt) },
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
    viewModel: BoardViewModel
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
                shape = RectangleShape,
                contentPadding = PaddingValues(16.dp),
                onClick = { viewModel.changeView(txt) },
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
            Text(
                text = "X: $x, Y: $y",
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Center,
                color = Color(255, 255, 255)
            )
        }
    }
}

@Preview
@Composable
fun PreviewFullBoardView() {
    FullBoardView(null, rememberNavController())
}