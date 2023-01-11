package com.mobilne.civ2077.ui.board

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilne.civ2077.data.Resource
import com.mobilne.civ2077.data.game.Game
import com.mobilne.civ2077.data.game.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BoardViewModel @Inject constructor(
    gameRepository: GameRepository
) : ViewModel() {

    private var _game = mutableStateOf(Game())
    val game: State<Game> = _game

    init {
        gameRepository.getGameRealtime()
            .onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _game.value = resource.result
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
}