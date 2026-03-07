package com.example.harmonyhub.features.music_player.player

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer

object Player {

    lateinit var exoPlayer: ExoPlayer;


    fun initialize(context: Context) {
        if (!::exoPlayer.isInitialized) {
            exoPlayer = ExoPlayer.Builder(context).build()
        }

    }

    fun getPlayer(): ExoPlayer {
        return exoPlayer ?: throw IllegalStateException("Player not initialized")
    }


}