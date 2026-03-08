package com.example.harmonyhub.features.playlist.presentation.state

import com.example.harmonyhub.features.playlist.data.remote.models.playlist.PlaylistData
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.PlaylistDetailsResponse

sealed class PlaylistDetailsUiState {
    data object Loading : PlaylistDetailsUiState()
    data class Success(val data: PlaylistData) : PlaylistDetailsUiState()
    data class Error(val message: String) : PlaylistDetailsUiState()

}