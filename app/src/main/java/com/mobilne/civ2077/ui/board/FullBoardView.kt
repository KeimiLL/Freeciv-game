package com.mobilne.civ2077.ui.board

import android.print.PrintAttributes.Margins
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

@Composable
fun FullBoardView() {
    Box(modifier = Modifier
        .background(Color(0xFFffc4a8))
        .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LeftSide()
            LeftSide()
            LeftSide()
//            ShowText("Ty")
//            ShowText("jakis dluzszy napis")
        }
    }
}

@Composable
fun LeftSide(
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(200.dp)
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceBetween

    ) {
//        Text(
//            text = "$txt",
//            style = MaterialTheme.typography.h5
//        )
        ButtonItem("Wyjście")
        ButtonItem("Złoto")
        ButtonItem("Zdj bazy")
    }
}

@Composable
fun ButtonItem(
    txt: String = "Nazwa-przycisku"
) {
    Button(
        modifier = Modifier.fillMaxWidth(0.7f)
            .width(150.dp),
        shape = RectangleShape,
        contentPadding = PaddingValues(16.dp),
        onClick = { /*TODO*/ },
    ) {
        Text(
            text = "Przycisk $txt",
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Center,
            color = Color(255,255,255)
        )
    }

}


@Composable
fun RightSideBtns(
    txt: String = "Kamil"
) {
    Column(
        modifier = Modifier
            .padding(15.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$txt",
            style = MaterialTheme.typography.h5
        )
        ButtonItem("Wyjście")
        ButtonItem("Złoto")
    }
}

@Preview
@Composable
fun PreviewFullBoardView() {
    FullBoardView()
}