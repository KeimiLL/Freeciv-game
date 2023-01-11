package com.mobilne.civ2077.data.auth

import com.google.firebase.auth.FirebaseUser
import com.mobilne.civ2077.data.Resource

interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun signup(name: String, email: String, password: String): Resource<FirebaseUser>
    fun logout()
}