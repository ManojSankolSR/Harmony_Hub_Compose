package com.example.harmonyhub.features.music_player.presentation.viewmodel

import android.app.Application
import android.content.ComponentName
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.example.harmonyhub.MusicPlayerService
import com.example.harmonyhub.features.music_player.data.repository.PlayerRepository
import com.example.harmonyhub.features.music_player.player.Player
import com.example.harmonyhub.features.music_player.presentation.state.MusicPlayerUiState
import com.example.harmonyhub.features.music_player.presentation.state.PlaybackState
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.toMediaItem
import com.google.common.util.concurrent.ListenableFuture
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MusicPlayerViewModel(
    application: Application,
    private val repository: PlayerRepository
) : AndroidViewModel(application) {

    private val context = application.applicationContext


    private val controllerFuture: ListenableFuture<MediaController>

    var controller: MediaController? = null
        private set


    private val playBackListner = object : androidx.media3.common.Player.Listener {

        override fun onPlaybackStateChanged(playbackState: Int) {

            when (playbackState) {

                androidx.media3.common.Player.STATE_BUFFERING -> loading()

                androidx.media3.common.Player.STATE_ENDED -> completed()

                androidx.media3.common.Player.STATE_IDLE -> {
                    _playerState.update {
                        it.copy(playbackState = PlaybackState.Error)
                    }
                }
            }
        }

        override fun onIsPlayingChanged(isPlaying: Boolean) {
            savePlayerStateToLocal()
            _playerState.update {
                it.copy(
                    playbackState =
                        if (isPlaying) PlaybackState.Playing
                        else PlaybackState.Paused
                )
            }
        }


    }

    override fun onCleared() {
        super.onCleared()
        savePlayerStateToLocal()
        removeListener()
    }

    init {
//
        val sessionToken = SessionToken(
            context,
            ComponentName(context, MusicPlayerService::class.java)
        )

        controllerFuture = MediaController.Builder(context, sessionToken).buildAsync()
        controllerFuture.addListener(
            { updateController() },
            ContextCompat.getMainExecutor(context)
        )

        getPlayerStateFromLocal()

    }

    private fun updateController() {
        try {
            controller = controllerFuture.get()
            addListener()
        } catch (e: Exception) {
            Log.e("MusicPlayerViewModel", "Failed to get controller", e)
        }
    }


    private val _playerState =
        MutableStateFlow<MusicPlayerUiState>(
            MusicPlayerUiState(
                null,
                PlaybackState.Loading,
                nextControlEnabled = true,
                prevControlEnabled = true,
                mediaItemQueue = emptyList()
            )
        )

    val playerState = _playerState.asStateFlow()

    val player: ExoPlayer = Player.getPlayer()

    fun play(songs: List<Song>?, index: Int? = 0) {
        val safeIndex = index ?: 0

        if (songs == null) {
            player.play()
            _playerState.update {
                it.copy(

                    nextControlEnabled = player.hasNextMediaItem(),
                    prevControlEnabled = player.hasPreviousMediaItem()
                )
            }
        } else {
            val mediaItems = songs.map { it.toMediaItem() }
            player.setMediaItems(mediaItems)
            player.seekTo(safeIndex, 0L)
            player.prepare()
            player.play()

            _playerState.update {
                it.copy(
                    mediaItemQueue = songs,
                    currentMediaItem = songs.getOrNull(safeIndex),
                    nextControlEnabled = songs.size > 1 && player.hasNextMediaItem(),
                    prevControlEnabled = safeIndex > 0 && player.hasPreviousMediaItem()
                )
            }

            Log.d("MusicPlayerViewModel", "play: ${player.hasNextMediaItem()}")
        }
    }

    fun pause() {
        player.pause()

    }

    fun completed() {
        _playerState.update {
            it.copy(playbackState = PlaybackState.Completed)
        }
    }

    fun loading() {
        _playerState.update {
            it.copy(playbackState = PlaybackState.Loading)
        }
    }

    fun nextMediaItem() {
        if (player.hasNextMediaItem()) {
            player.seekToNextMediaItem()
            player.prepare()
            player.play()
            updateUiState()
        }
    }

    fun prevMediaItem() {
        if (player.hasPreviousMediaItem()) {
            player.seekToPreviousMediaItem()
            player.prepare()
            player.play()
            updateUiState()
        }
    }

    private fun updateUiState() {
        val currentIndex = player.currentMediaItemIndex
        val currentMediaItem = playerState.value.mediaItemQueue.getOrNull(currentIndex)

        _playerState.update {
            it.copy(
                currentMediaItem = currentMediaItem,
                nextControlEnabled = player.hasNextMediaItem(),
                prevControlEnabled = player.hasPreviousMediaItem()
            )
        }
    }

    fun replay(isError: Boolean = false) {
        if (isError) {
            player.seekTo(0, 0L)
            player.prepare()
            player.play()
            return
        } else {
            player.seekTo(0, 0L)
            player.prepare()
            player.play()
            _playerState.update {
                it.copy(

                    currentMediaItem = playerState.value.mediaItemQueue.getOrNull(0),
                    nextControlEnabled = player.hasNextMediaItem(),
                    prevControlEnabled = true
                )
            }
        }

    }


    fun seekTo(position: Long) {
        player.seekTo(position)
    }


    fun addListener() {

        player.addListener(
            playBackListner
        )
    }

    fun removeListener() {

        player.removeListener(
            playBackListner
        )
    }


    fun savePlayerStateToLocal() {
        Log.d("getPlayerStateFromLocal ", "togetPlayerStateFromLocal: ${_playerState.value}")

        viewModelScope.launch {
            val state = _playerState.value
            state.currentMediaItem?.let { song ->
                repository.savePlayerState(state.mediaItemQueue, song.id)
            }
        }
    }


    fun getPlayerStateFromLocal() {
        viewModelScope.launch {
            val state = repository.getPlayerState()
            Log.d("getPlayerStateFromLocal ", "getPlayerStateFromLocal: $state")
            _playerState.update {state}
            play(state.mediaItemQueue, state.mediaItemQueue.indexOf(state.currentMediaItem))
            pause()
        }

    }
}

class MusicPlayerViewModelFactory(
    private val application: Application,
    private val repository: PlayerRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MusicPlayerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MusicPlayerViewModel(application, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
