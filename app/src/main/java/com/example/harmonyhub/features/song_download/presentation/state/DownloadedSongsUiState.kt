package com.example.harmonyhub.features.song_download.presentation.state

import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song

sealed class DownloadedSongsUiState {
    data class Success(val songs: List<Song>) : DownloadedSongsUiState()
    object Loading : DownloadedSongsUiState()
    data class Error(val message: String) : DownloadedSongsUiState()
}