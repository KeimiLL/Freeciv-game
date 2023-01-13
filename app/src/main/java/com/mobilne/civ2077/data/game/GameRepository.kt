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

    /**
     * Default:
     *
     * player<:index>: {
     *  armyPosition: {
     *   x: 0,
     *   y: 0
     *  },
     *  armySize: 0,
     *  basePosition: {
     *   x: 0,
     *   y: 0
     *  },
     *  dev: {
     *   left: 0,
     *   right: 0
     *  },
     *  gold: 1000,
     *  nation: ""
     * }
     */
    fun getPlayerWithIndexRealTime(index: Int): Flow<Resource<Player>>
    fun savePlayerNationChoice(index: Int, nation: String)
}