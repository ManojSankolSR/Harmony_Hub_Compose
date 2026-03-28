package com.example.harmonyhub.features.music_player.presentation.viewmodel

import com.example.harmonyhub.features.music_player.presentation.player.MediaControllerManager
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.exoplayer.ExoPlayer
import com.example.harmonyhub.core.data.respository.SongRepository
import com.example.harmonyhub.features.auth.data.respository.UserRepository
import com.example.harmonyhub.core.models.AudioQuality
import com.example.harmonyhub.core.models.SnackBar
import com.example.harmonyhub.core.services.LoaderManager
import com.example.harmonyhub.core.services.SnackBarManager
import com.example.harmonyhub.features.home.data.remote.models.MusicDataItem
import com.example.harmonyhub.features.music_player.data.repository.PlayerRepository
import com.example.harmonyhub.features.music_player.player.Player
import com.example.harmonyhub.features.music_player.presentation.player.PlayBackStateController
import com.example.harmonyhub.features.music_player.presentation.player.PlaybackListener
import com.example.harmonyhub.features.music_player.presentation.state.MusicPlayerUiState
import com.example.harmonyhub.features.music_player.presentation.state.PlaybackState
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song
import com.example.harmonyhub.features.radio.data.repository.RadioRepository
import com.example.harmonyhub.features.recomendations.data.repository.RecommendationsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MusicPlayerViewModel(
    application: Application,
    private val repository: PlayerRepository,
    private val userRepository: UserRepository,
    private val radioRepository: RadioRepository,
    private val songRepository: SongRepository,
    private val recommendationsRepository: RecommendationsRepository
) : AndroidViewModel(application) {


    val player: ExoPlayer = Player.getPlayer()

    private val user =
        userRepository.getUser().stateIn(viewModelScope, SharingStarted.Eagerly, null)

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
        try {
            val quality = user.value?.preferredAudioQuality!!;
            Log.d("MusicPlayerViewModel", "setMediaItems: ${user.value}")
            playBackStateController.setMediaItems(songs, startIndex, quality)
        } catch (e: Exception) {
            viewModelScope.launch {
                SnackBarManager.show(
                    SnackBar.ErrorSnackBar(
                        title = "Error",
                        description = e.message ?: "Something went wrong",
                        duration = StackedSnackbarDuration.Short
                    )
                )
            }
        }
    }

    fun addMediaItemsToQueue(songs: List<Song>) {
        try {
            val quality = user.value?.preferredAudioQuality!!;
            playBackStateController.addMediaItemsToQueue(songs, quality)
        } catch (e: Exception) {
            viewModelScope.launch {
                SnackBarManager.show(
                    SnackBar.ErrorSnackBar(
                        title = "Error",
                        description = e.message ?: "Something went wrong",
                        duration = StackedSnackbarDuration.Short
                    )
                )
            }
        }

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

    fun skip(milliseconds: Long) {
        playBackStateController.skip(milliseconds)
    }

    fun shuffle() {
        val quality = user.value?.preferredAudioQuality!!;
        val currentQueue = playerState.value.mediaItemQueue;
        playBackStateController.shuffle(currentQueue, quality)
    }

    fun playRadio(item: MusicDataItem) {
        viewModelScope.launch {
            LoaderManager.show()
            try {
                val songs = radioRepository.createRadioStationAndGetSongs(item)
                setMediaItems(songs)
                play()
            } catch (e: Exception) {
                SnackBarManager.show(
                    SnackBar.ErrorSnackBar(
                        title = "Error",
                        description = e.message ?: "Something went wrong",
                        duration = StackedSnackbarDuration.Short
                    )
                )
            } finally {
                LoaderManager.hide()
            }
        }
    }

    fun fetchAndPlaySongs(id: String) {
        viewModelScope.launch {
            LoaderManager.show()
            try {
                val songs = songRepository.getSongs(id)
                setMediaItems(songs)
                fetchAndAddSongRecommendationsToQueue()
                play()
            } catch (e: Exception) {
                SnackBarManager.show(
                    SnackBar.ErrorSnackBar(
                        title = "Error",
                        description = e.message ?: "Something went wrong",
                        duration = StackedSnackbarDuration.Short
                    )
                )
            } finally {
                LoaderManager.hide()
            }
        }
    }


    fun fetchAndAddSongRecommendationsToQueue() {
        viewModelScope.launch {
            try {
                val currentMediaItem = playerState.value.currentMediaItem;
                val id = currentMediaItem?.id ?: return@launch;
                val languages = currentMediaItem.language ?: "";
                val songs = recommendationsRepository.getSongRecommendations(id, languages)
                addMediaItemsToQueue(songs)
            } catch (e: Exception) {
//                SnackBarManager.show(
//                    SnackBar.ErrorSnackBar(
//                        title = "Error",
//                        description = e.message ?: "Something went wrong",
//                        duration = StackedSnackbarDuration.Short
//                    )
//                )
            }
        }

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

    private fun addDurationListener() {
        viewModelScope.launch {
            playBackStateController.addDurationListener();
        }

    }


    private fun savePlayerStateToLocal() {
        viewModelScope.launch {
            val state = _playerState.value
            state.currentMediaItem?.let { song ->
                repository.savePlayerState(
                    state.mediaItemQueue,
                    song.id,
                    state.currentPosition,
                    state.duration
                )
            }
        }
    }


    private fun getPlayerStateFromLocal() {

        val quality = user.value?.preferredAudioQuality ?: AudioQuality.medium;

        viewModelScope.launch {
            val state = repository.getPlayerState()
            playBackStateController.setMediaItems(
                state.mediaItemQueue,
                state.mediaItemQueue.indexOf(state.currentMediaItem),
                quality
            )
            seekTo(state.currentPosition)
            player.prepare()
            updateUiState { state }
        }

    }
}


