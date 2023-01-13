package com.mobilne.civ2077.data.game

import com.google.firebase.database.DatabaseReference
import com.mobilne.civ2077.data.Resource
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    var databaseRef: DatabaseReference

    /**
     * Default:
     *
     * game_state {
     *  full: false,
     *  in_progress: false,
     *  waiting: true,
     * }
     */
    fun getGameStateOnce(): Flow<Resource<GameState>>
    fun getGameStateRealtime(): Flow<Resource<GameState>>

    /**
     * Default:
     *
     * current_turn_uuid: ""
     */
    fun getCurrentTurnUidRealtime(): Flow<Resource<String>>

    /**
     * Default:
     *
     * players {
     *  uuid1: "",
     *  uuid2: "",
     *  uuid3: "",
     * }
     */
    fun getPlayersRealtime(): Flow<Resource<Players>>
    fun addPlayerToTheGame(players: Players)
}