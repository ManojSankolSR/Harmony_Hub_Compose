package com.example.harmonyhub.features.music_player.presentation.viewmodel

import com.example.harmonyhub.features.music_player.presentation.player.MediaControllerManager
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.exoplayer.ExoPlayer
import com.example.harmonyhub.features.music_player.data.repository.PlayerRepository
import com.example.harmonyhub.features.music_player.player.Player
import com.example.harmonyhub.features.music_player.presentation.player.PlayBackStateController
import com.example.harmonyhub.features.music_player.presentation.player.PlaybackListener
import com.example.harmonyhub.features.music_player.presentation.state.MusicPlayerUiState
import com.example.harmonyhub.features.music_player.presentation.state.PlaybackState
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MusicPlayerViewModel(
    application: Application,
    private val repository: PlayerRepository
) : AndroidViewModel(application) {

    val player: ExoPlayer = Player.getPlayer()
    private val context = application.applicationContext
    private val playBackStateController = PlayBackStateController(player, ::updateUiState)
    private val playBackListener =
        PlaybackListener(
            player,
            ::updateUiState,
            ::updateCurrentMediaItem,
            ::savePlayerStateToLocal
        )
    private val controllerManager = MediaControllerManager(
        context,
        playBackListener
    )
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

    init {
        getPlayerStateFromLocal()
        addDurationListener()
    }

    override fun onCleared() {
        super.onCleared()
        savePlayerStateToLocal()
        removeListener()
        controllerManager.release()
    }

    private fun updateUiState(
        reducer: MusicPlayerUiState.() -> MusicPlayerUiState
    ) {
        _playerState.update { it.reducer() }
    }

    private fun updateCurrentMediaItem(index: Int) {
        val currentMediaItem = _playerState.value.mediaItemQueue.getOrNull(index)

        updateUiState {
            copy(currentMediaItem = currentMediaItem)
        }

    }

    fun play() {
        playBackStateController.play()
    }

    fun setMediaItems(songs: List<Song>, startIndex: Int = 0) {
        playBackStateController.setMediaItems(songs, startIndex)
    }

    fun pause() {
        playBackStateController.pause()
    }

    fun nextMediaItem() {
        playBackStateController.nextMediaItem()
    }

    fun prevMediaItem() {
        playBackStateController.prevMediaItem()
    }

    fun replay(isError: Boolean = false) {
        playBackStateController.replay(isError)
    }


    fun seekTo(position: Long) {
        playBackStateController.seekTo(position)
    }

    fun skip(milliseconds: Long){
        playBackStateController.skip(milliseconds)
    }

    fun shuffle(){
        val currentQueue=playerState.value.mediaItemQueue;
        playBackStateController.shuffle(currentQueue)
    }


    fun addListener() {
        player.addListener(
            playBackListener
        )
    }

    fun removeListener() {
        player.removeListener(
            playBackListener
        )
    }

    private fun addDurationListener(){
        viewModelScope.launch {
            playBackStateController.addDurationListener();
        }

    }


    private fun savePlayerStateToLocal() {
        viewModelScope.launch {
            val state = _playerState.value
            state.currentMediaItem?.let { song ->
                repository.savePlayerState(state.mediaItemQueue, song.id,state.currentPosition,state.duration)
            }
        }
    }


    private fun getPlayerStateFromLocal() {
        viewModelScope.launch {
            val state = repository.getPlayerState()
            playBackStateController.setMediaItems(
                state.mediaItemQueue,
                state.mediaItemQueue.indexOf(state.currentMediaItem)
            )
            seekTo(state.currentPosition)
            player.prepare()
            updateUiState { state }
        }

    }
}


