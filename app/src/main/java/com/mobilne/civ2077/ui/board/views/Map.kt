package com.mobilne.civ2077.ui.board

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobilne.civ2077.R


@Composable
fun Map() {
    Surface(shadowElevation = 4.dp, shape = RoundedCornerShape(16.dp)) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentWidth()
                .background(Color.White)
        ) {
            Image(
                painter = painterResource(id = R.drawable.map),
                contentDescription = "Map"
            )
        }
    }
}

@Preview
@Composable
fun PreviewMap() {
    Map()
}