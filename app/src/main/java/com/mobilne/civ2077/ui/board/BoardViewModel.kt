package com.mobilne.civ2077.ui.board

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilne.civ2077.data.Resource
import com.mobilne.civ2077.data.auth.AuthRepository
import com.mobilne.civ2077.data.game.GameRepository
import com.mobilne.civ2077.data.game.GameState
import com.mobilne.civ2077.data.game.Players
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BoardViewModel @Inject constructor(
    private val gameRepository: GameRepository,
    private val authRepository: AuthRepository,
) : ViewModel() {

    private var _gameState = mutableStateOf(GameState())
    val gameState: State<GameState> = _gameState

    private var _currentTurnUid = mutableStateOf("")
    val currentTurnUid: State<String> = _currentTurnUid

    private var _players = mutableStateOf(Players())
    val players: State<Players> = _players

    var currentView by mutableStateOf("Mapa")


    init {
        initGameStateFlow()
        initCurrentTurnUid()
        initPlayers()
    }

    private fun initGameStateFlow() {
        gameRepository.getGameStateRealtime()
            .onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _gameState.value = resource.result
                    }
                    is Resource.Failure -> {
                        Log.d(TAG, "failure")
                    }
                    is Resource.Loading -> {
                        Log.d(TAG, "loading")
                    }
                }

            }
            .launchIn(viewModelScope)
    }

    private fun initCurrentTurnUid() {
        gameRepository.getCurrentTurnUidRealtime()
            .onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _currentTurnUid.value = resource.result
                    }
                    is Resource.Failure -> {
                        Log.d(TAG, "failure")
                    }
                    is Resource.Loading -> {
                        Log.d(TAG, "loading")
                    }
                }

            }
            .launchIn(viewModelScope)
    }

    private fun initPlayers() {
        gameRepository.getPlayersRealtime()
            .onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _players.value = resource.result
                    }
                    is Resource.Failure -> {
                        Log.d(TAG, "failure")
                    }
                    is Resource.Loading -> {
                        Log.d(TAG, "loading")
                    }
                }

            }
            .launchIn(viewModelScope)
    }

    fun changeView(newView: String){
        currentView = newView
    }


    fun isUserAddedToTheGame(): Boolean {
        val uid = authRepository.currentUser!!.uid
        return (
                uid === players.value.uid1 ||
                        uid === players.value.uid2 ||
                        uid === players.value.uid3
                )
    }

    fun addUserToTheGame() {
        val uid = authRepository.currentUser!!.uid
        val uidsToSave = mutableListOf<String?>()
        if (players.value.uid1.isNullOrEmpty()) {
            uidsToSave.add(uid)
            uidsToSave.add("")
            uidsToSave.add("")
        } else if (players.value.uid2.isNullOrEmpty()) {
            uidsToSave.add(players.value.uid1)
            uidsToSave.add(uid)
            uidsToSave.add("")
        } else if (players.value.uid3.isNullOrEmpty()) {
            uidsToSave.add(players.value.uid1)
            uidsToSave.add(players.value.uid2)
            uidsToSave.add(uid)
        }

        val playersToSave = Players(
            uidsToSave[0],
            uidsToSave[1],
            uidsToSave[2],
        )

        gameRepository.addPlayerToTheGame(playersToSave)
    }
}