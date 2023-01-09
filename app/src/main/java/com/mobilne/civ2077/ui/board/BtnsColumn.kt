package com.mobilne.civ2077.ui.board

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BtnsColumn() {
    Box(modifier = Modifier
        .background(Color(0xFFffc4a8))
        .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
//            ButtonItem("Ja")
//            DisplayText("Ty")
//            DisplayText("jakis dluzszy napis")
        }
    }
}

@Preview
@Composable
fun PreviewBtnsColumn() {
    FullBoardView()
}