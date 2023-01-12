package com.mobilne.civ2077.data.game

data class GameState(
    val full: Boolean = false,
    val in_progress: Boolean = false,
    val waiting: Boolean = false,
)

data class Players(
    val uid1: String? = null,
    val uid2: String? = null,
    val uid3: String? = null,
)
