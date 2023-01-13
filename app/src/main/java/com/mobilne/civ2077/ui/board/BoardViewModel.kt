package com.mobilne.civ2077.ui.board

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.MutableState
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
import com.mobilne.civ2077.data.game.Player
import com.mobilne.civ2077.data.game.Players
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
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

    private var _player1 = mutableStateOf(Player())
    val player1: State<Player> = _player1

    private var _player2 = mutableStateOf(Player())
    val player2: State<Player> = _player2

    private var _player3 = mutableStateOf(Player())
    val player3: State<Player> = _player3

    private var _currentPlayerIndex = mutableStateOf(0)
    val currentPlayerIndex: State<Int> = _currentPlayerIndex

    var currentNationChoice = ""

    var currentView by mutableStateOf("Mapa")

    init {
        initGameState()
        initCurrentTurnUid()
        initPlayerCollection()
        initPlayer(1, _player1)
        initPlayer(2, _player2)
        initPlayer(3, _player3)
    }

    private fun initGameState() {
        gameRepository.getGameStateRealtime().onEach { resource ->
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
        }.launchIn(viewModelScope)
    }

    private fun initCurrentTurnUid() {
        gameRepository.getCurrentTurnUidRealtime().onEach { resource ->
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
        }.launchIn(viewModelScope)
    }

    private fun initPlayerCollection() {
        gameRepository.getPlayersRealtime().onEach { resource ->
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
        }.launchIn(viewModelScope)
    }

    private fun initPlayer(index: Int, targetVariable: MutableState<Player>) {
        gameRepository.getPlayerWithIndexRealTime(index).onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    targetVariable.value = resource.result
                }
                is Resource.Failure -> {
                    Log.d(TAG, "failure")
                }
                is Resource.Loading -> {
                    Log.d(TAG, "loading")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun changeView(newView: String){
        currentView = newView
    }

    fun isPlayerAddedToTheGame(): Boolean {
        val uid = authRepository.currentUser!!.uid
        return (uid === players.value.uid1 || uid === players.value.uid2 || uid === players.value.uid3)

    }

    private fun getPlayerIndexByUid(): Int {
        val uid = authRepository.currentUser!!.uid
        if (uid.isNotEmpty()) {
            return when (uid) {
                players.value.uid1 -> 1
                players.value.uid2 -> 2
                players.value.uid3 -> 3
                else -> 0
            }
        }
        return 0
    }

    fun setPlayerIndex() {
        _currentPlayerIndex.value = getPlayerIndexByUid()
    }

    suspend fun checkAddingPlayer() {
        if (!isPlayerAddedToTheGame()) {
            addPlayerToTheGame()
        } else {
            setPlayerIndex()
        }
    }

    suspend fun addPlayerToTheGame() {
        val uid = authRepository.currentUser!!.uid
        val uidsToSave = mutableListOf<String>()

        if (players.value.uid1.isEmpty()) {
            uidsToSave.add(uid)
            uidsToSave.add("")
            uidsToSave.add("")
            _currentPlayerIndex.value = 1
        } else if (players.value.uid2.isEmpty()) {
            uidsToSave.add(players.value.uid1)
            uidsToSave.add(uid)
            uidsToSave.add("")
            _currentPlayerIndex.value = 2
        } else if (players.value.uid3.isEmpty()) {
            uidsToSave.add(players.value.uid1)
            uidsToSave.add(players.value.uid2)
            uidsToSave.add(uid)
            _currentPlayerIndex.value = 3
        } else {
            uidsToSave.add(players.value.uid1)
            uidsToSave.add(players.value.uid2)
            uidsToSave.add(players.value.uid3)
        }

        val playersToSave = Players(
            uidsToSave[0],
            uidsToSave[1],
            uidsToSave[2],
        )

        coroutineScope {
            gameRepository.addPlayerToTheGame(playersToSave)
            delay(800L)
            setPlayerIndex()
        }
    }

    fun getPlayerByIndex(index: Int): Player {
        return when (index) {
            1 -> player1.value
            2 -> player2.value
            3 -> player3.value
            else -> Player()
        }
    }

    fun getCurrentPlayerNationChoice(): String {
        return getPlayerByIndex(currentPlayerIndex.value).nation
    }

    fun didPlayerMakeNationChoice(): Boolean {
        return getCurrentPlayerNationChoice().isNotEmpty()
    }

    fun savePlayerNationChoice() {
        gameRepository.savePlayerNationChoice(currentPlayerIndex.value, currentNationChoice)
    }

    fun onNationChange(nation: String) {
        currentNationChoice = nation
    }
}