package com.example.harmonyhub

import android.app.Application
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import com.example.harmonyhub.core.data.local.db.AppDatabase
import com.example.harmonyhub.di.AppContainer
import com.example.harmonyhub.features.music_player.player.Player

class HarmonyHub : Application() {
    lateinit var appContainer: AppContainer;
    override fun onCreate() {
        super.onCreate()
        Player.initialize(this)
        appContainer = AppContainer(this)
    }
}
