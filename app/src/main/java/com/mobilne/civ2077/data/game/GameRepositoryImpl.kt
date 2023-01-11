package com.mobilne.civ2077.data.game

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.mobilne.civ2077.data.Resource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class GameRepositoryImpl(
    private val database: FirebaseDatabase
) : GameRepository {

    override fun getGameOnce(): Flow<Resource<Game>> = callbackFlow {
        // TODO: add handling the whole database, not just the game_state
        // maybe split it into different methods for different parts of the database?
        database.getReference("game_state")
            .get()
            .addOnCompleteListener { task ->
                val response = if (task.isSuccessful) {
                    val game = task.result.getValue<Game>()!!
                    Resource.Success(game)
                } else {
                    Resource.Failure(Exception(task.exception?.localizedMessage.toString()))
                }
                trySend(response).isSuccess
            }

        awaitClose {
            close()
        }
    }

    override fun getGameRealtime(): Flow<Resource<Game>> = callbackFlow {
        // TODO: add handling the whole database, not just the game_state
        database.getReference("game_state")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val game = dataSnapshot.getValue<Game>()!!
                    trySend(Resource.Success(game)).isSuccess
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
}