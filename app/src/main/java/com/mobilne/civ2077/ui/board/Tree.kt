package com.mobilne.civ2077.ui.board

import android.print.PrintAttributes.Margins
import androidx.compose.animation.expandVertically
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

@Composable
fun Tree() {
    Box(modifier = Modifier
        .background(Color(0xFFfdebea))
        .fillMaxSize()
    ) {
        Column(verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally) {
            Column(modifier = Modifier
                .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.Center) {
                    Text(text = "Ekonomia", color = Color.Blue, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly) {
                    RoundItem(1, "Eko")
                    RoundItem(2, "Eko")
                    RoundItem(3, "Eko")
                    RoundItem(4, "Eko")
                }
            }
            Column(modifier = Modifier
                .weight(1f)) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                    horizontalArrangement = Arrangement.Center) {
                    Text(text = "Wojsko", color = Color.Red, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly) {
                    RoundItem(1, "Woj")
                    RoundItem(2, "Woj")
                    RoundItem(3, "Woj")
                    RoundItem(4, "Woj")
                }
            }
            Row(modifier = Modifier
                .weight(1f),
            verticalAlignment = Alignment.Bottom) {
                InfoBar(1,"Ekonomia")
            }
        }
    }
}

@Composable
fun RoundItem(
    id: Int = 1,
    txt: String = "Ekonomia") {
    Button(
        modifier = Modifier
            .height(80.dp)
            .width(80.dp),
        shape = RectangleShape,
        contentPadding = PaddingValues(16.dp),
        onClick = { /*TODO*/ },
    ) {
        Text(
            text = "$txt $id",
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Center,
            color = Color(255,255,255)
        )
    }
}

@Composable
fun InfoBar(
    id: Int = 1,
    txt: String = "Ekonomia") {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp),
    horizontalArrangement = Arrangement.SpaceEvenly,
    verticalAlignment = Alignment.CenterVertically) {
        Text(text = "Info o turze $id dotyczacej zakladki $txt.")
        Button(
            modifier = Modifier
                .height(60.dp)
                .width(60.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff507d00)),
            shape = RectangleShape,
            contentPadding = PaddingValues(16.dp),
            onClick = { /*TODO*/ },
        ) {
            Text(
                text = "Kup",
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Center,
                color = Color(255,255,255)
            )
        }
    }
}

@Preview
@Composable
fun PreviewTree() {
    Tree()
}