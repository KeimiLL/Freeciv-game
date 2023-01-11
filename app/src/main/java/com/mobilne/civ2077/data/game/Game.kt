package com.mobilne.civ2077.data.game

data class Game(
    // TODO: add handling the whole database, not just the game_state
    val full: Boolean = false,
    val in_progress: Boolean = false,
    val waiting: Boolean = false,
)
