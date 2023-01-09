package com.mobilne.civ2077.ui.board

import android.graphics.Paint.Align
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
fun Board() {
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
            ShowText("Ja")
            ShowText("Ty")
            ShowText("jakis dluzszy napis")
        }
    }
}

@Composable
fun ShowText(
    txt: String = "Kamil"
) {
    Column(
        modifier = Modifier
            .padding(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "$txt",
                style = MaterialTheme.typography.h5
            )
        
        }
    }
}

@Preview
@Composable
fun PreviewBoard() {
    Board()
}