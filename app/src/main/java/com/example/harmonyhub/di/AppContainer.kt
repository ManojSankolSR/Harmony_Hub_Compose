package com.example.harmonyhub.di

import android.content.Context
import com.example.harmonyhub.core.data.local.db.AppDatabase
import com.example.harmonyhub.core.data.respository.UserRepository
import com.example.harmonyhub.features.home.data.respository.HomeRepository
import com.example.harmonyhub.features.playlist.data.respository.PlaylistRepository

class AppContainer(val context: Context) {

    val db: AppDatabase = AppDatabase.getInstance(context)

    val userRepository = UserRepository(db.userDao())

    val homeRepository = HomeRepository();

    val playlistRepository = PlaylistRepository();

}