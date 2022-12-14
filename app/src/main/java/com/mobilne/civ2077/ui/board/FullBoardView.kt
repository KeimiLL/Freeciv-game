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
import com.mobilne.civ2077.ui.buyGoldDialog.BuyGoldDialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.mobilne.civ2077.ui.sendArmyDialog.SendArmyDialog
import com.mobilne.civ2077.ui.buyGoldDialog.BuyGoldDialogViewModel

@Composable
fun FullBoardView(
    viewModel: BoardViewModel = hiltViewModel()
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
                ButtonItem(viewModel.game.value.waiting.toString())
                ButtonItem("Wyjście")
                ButtonItem("Złoto")
                ButtonXYItem("Zdj bazy", 55, 60)
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .weight(4f)
                    .padding(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Map()
//                Tree()
//                SendArmyDialog(viewModel = SendArmyViewModel())
//                BuyGoldDialog(viewModel = BuyGoldDialogViewModel())
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(5.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                ButtonItem("Tura")
                ButtonItem("Rozwoj")
                ButtonXYItem("Zdj wojska", 75, 85)
            }
        }
    }
}


@Composable
fun ButtonItem(
    txt: String = "Nazwa-przycisku"
) {
    Button(
        modifier = Modifier
            .height(80.dp)
            .width(110.dp),
        shape = RectangleShape,
        contentPadding = PaddingValues(16.dp),
        onClick = { /*TODO*/ },
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
    y: Int = 0
) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Bottom
    ) {
        Row {
            Button(
                modifier = Modifier
                    .height(80.dp)
                    .width(110.dp),
                shape = RectangleShape,
                contentPadding = PaddingValues(16.dp),
                onClick = { /*TODO*/ },
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
                .width(110.dp),
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
    FullBoardView()
}