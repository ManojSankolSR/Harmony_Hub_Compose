package com.example.harmonyhub.features.like.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harmonyhub.core.models.SnackBar
import com.example.harmonyhub.core.services.SnackBarManager
import com.example.harmonyhub.features.like.data.repository.LikedSongsRepository
import com.example.harmonyhub.features.like.presentation.state.LikedSongsUIState
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LikedSongsViewModel(private val repository: LikedSongsRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<LikedSongsUIState>(LikedSongsUIState.Loading)
    val state = _uiState.asStateFlow()

    init {
        observeLikedSongs()
    }

    fun observeLikedSongs() {
        try {
            viewModelScope.launch {
                repository.observeLikedSongs().collect { songs ->
                    _uiState.update {
                        LikedSongsUIState.Success(songs)
                    }
                }
            }
        } catch (e: Exception) {
            _uiState.update {
                LikedSongsUIState.Error(e.message ?: "Unknown Error")
            }
        }
    }

    fun unLikeSong(songId: String) {
        viewModelScope.launch {
            try {
                repository.deleteLikedSong(songId)
            } catch (e: Exception) {
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

    fun likeSong(song: Song) {
        viewModelScope.launch {
            try {
                repository.addLikedSong(song)
            } catch (e: Exception) {
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


}