package com.mobilne.civ2077.ui.board

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mobilne.civ2077.ui.theme.md_theme_dark_inverseSurface

@Composable
fun Board() {
    Box(
        modifier = Modifier
            .background(md_theme_dark_inverseSurface)
            .fillMaxSize()
    )
}

@Preview
@Composable
fun PreviewBoard() {
    Board()
}