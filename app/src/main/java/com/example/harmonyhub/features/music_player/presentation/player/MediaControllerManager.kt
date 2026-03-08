package com.example.harmonyhub.features.music_player.presentation.player

import android.content.ComponentName
import android.content.Context
import androidx.core.content.ContextCompat
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.example.harmonyhub.MusicPlayerService

import com.google.common.util.concurrent.ListenableFuture

class MediaControllerManager(
    context: Context,
    private val playerListener: Player.Listener
) {

    private val controllerFuture: ListenableFuture<MediaController>

    var controller: MediaController? = null
        private set

    init {
        val sessionToken = SessionToken(
            context,
            ComponentName(context, MusicPlayerService::class.java)
        )

        controllerFuture = MediaController.Builder(context, sessionToken).buildAsync()

        controllerFuture.addListener({

            controller = controllerFuture.get()

            controller?.addListener(playerListener)

        }, ContextCompat.getMainExecutor(context))
    }

    fun release() {
        controller?.removeListener(playerListener)
        controllerFuture.cancel(true)
    }
}