package com.example.harmonyhub.features.song_download.presentation.state

sealed class SongsDownloadUIState {
    data class Downloading(val progress: Double) : SongsDownloadUIState()
    data object Success : SongsDownloadUIState()
    data class Error(val message: String) : SongsDownloadUIState()
}