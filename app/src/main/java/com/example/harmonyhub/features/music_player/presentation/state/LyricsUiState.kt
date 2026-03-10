package com.example.harmonyhub.features.music_player.presentation.state

import com.example.harmonyhub.features.music_player.data.remote.models.Lyrics

sealed class LyricsUiState {
    data object Loading : LyricsUiState()
    data class Success(val lyrics: Lyrics) : LyricsUiState()
    data class Error(val message: String) : LyricsUiState()

}