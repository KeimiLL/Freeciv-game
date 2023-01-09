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
fun Tree() {
    Box(modifier = Modifier
        .background(Color(0xFFfae6e9))
        .fillMaxSize()
    ) {
        Text(text = "Miejsce na dodanie drzewka")
    }
}

@Preview
@Composable
fun PreviewTree() {
    Tree()
}