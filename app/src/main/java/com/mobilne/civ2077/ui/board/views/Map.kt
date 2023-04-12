package com.mobilne.civ2077.ui.board

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.mobilne.civ2077.R


@Composable
fun Map() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .wrapContentWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.map),
            contentDescription = "Map"
        )
    }
}

@Preview
@Composable
fun PreviewMap() {
    Map()
}