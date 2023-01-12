package com.mobilne.civ2077.data.game

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.mobilne.civ2077.data.Resource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class GameRepositoryImpl(
    private val database: FirebaseDatabase
) : GameRepository {

    override var databaseRef: DatabaseReference = Firebase.database.reference

    override fun getGameStateOnce(): Flow<Resource<GameState>> = callbackFlow {
        database.getReference("game_state")
            .get()
            .addOnCompleteListener { task ->
                val response = if (task.isSuccessful) {
                    val gameState = task.result.getValue<GameState>()!!
                    Resource.Success(gameState)
                } else {
                    Resource.Failure(Exception(task.exception?.localizedMessage.toString()))
                }
                trySend(response).isSuccess
            }

        awaitClose {
            close()
        }
    }

    override fun getGameStateRealtime(): Flow<Resource<GameState>> = callbackFlow {
        database.getReference("game_state")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val gameState = dataSnapshot.getValue<GameState>()!!
                    trySend(Resource.Success(gameState)).isSuccess
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w("game", "Failed to read value.")
                    trySend(Resource.Failure(Exception(error.message))).isFailure
                }
            })

        awaitClose {
            close()
        }
    }

    override fun getCurrentTurnUidRealtime(): Flow<Resource<String>> = callbackFlow {
        database.getReference("current_turn_uid")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val currentTurnUuid = dataSnapshot.getValue<String>()!!
                    trySend(Resource.Success(currentTurnUuid)).isSuccess
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w("game", "Failed to read value.")
                    trySend(Resource.Failure(Exception(error.message))).isFailure
                }
            })

        awaitClose {
            close()
        }
    }

    override fun getPlayersRealtime(): Flow<Resource<Players>> = callbackFlow {
        database.getReference("players")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val players = dataSnapshot.getValue<Players>()!!
                    trySend(Resource.Success(players)).isSuccess
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w("game", "Failed to read value.")
                    trySend(Resource.Failure(Exception(error.message))).isFailure
                }
            })

        awaitClose {
            close()
        }
    }

    override fun addPlayerToTheGame(players: Players) {
        Log.d(TAG, "players: ${players.uid1}, ${players.uid2}, ${players.uid3}")
        databaseRef.child("players").setValue(players)
            .addOnSuccessListener {
                Log.d(TAG, "Saved successfully")
            }
            .addOnFailureListener {
                Log.d(TAG, "Failed to save value: ${it.message}")
            }
    }
}