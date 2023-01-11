package com.mobilne.civ2077.data.game

import com.mobilne.civ2077.data.Resource
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun getGameOnce(): Flow<Resource<Game>>
    fun getGameRealtime(): Flow<Resource<Game>>
}