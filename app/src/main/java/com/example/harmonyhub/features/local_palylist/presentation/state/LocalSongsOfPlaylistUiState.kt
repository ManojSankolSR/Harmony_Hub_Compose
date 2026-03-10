package com.example.harmonyhub.features.local_palylist.presentation.state

import com.example.harmonyhub.features.music_player.data.local.entities.SongEntity
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song

sealed class LocalSongsOfPlaylistUiState {
    data object Loading : LocalSongsOfPlaylistUiState()
    data class Success(val data: List<Song>) : LocalSongsOfPlaylistUiState()
    data class Error(val message: String) : LocalSongsOfPlaylistUiState()
}