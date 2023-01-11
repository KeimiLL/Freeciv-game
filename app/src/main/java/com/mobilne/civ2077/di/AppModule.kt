package com.mobilne.civ2077.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mobilne.civ2077.data.auth.AuthRepository
import com.mobilne.civ2077.data.auth.AuthRepositoryImpl
import com.mobilne.civ2077.data.game.GameRepository
import com.mobilne.civ2077.data.game.GameRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    @Singleton
    fun provideGameRepository(database: FirebaseDatabase): GameRepository =
        GameRepositoryImpl(database)

    @Provides
    @Singleton
    fun provideRealtimeDatabase(): FirebaseDatabase =
        Firebase.database("https://civ-2077-4c0b6-default-rtdb.europe-west1.firebasedatabase.app")
}