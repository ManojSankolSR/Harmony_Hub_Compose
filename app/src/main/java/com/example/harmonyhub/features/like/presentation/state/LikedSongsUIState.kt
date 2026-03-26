package com.example.harmonyhub.features.like.presentation.state

import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song

sealed class LikedSongsUIState {
    data object Loading: LikedSongsUIState()
    data class Success(val songs: List<Song>) : LikedSongsUIState()
    data class Error(val message: String) : LikedSongsUIState()
}