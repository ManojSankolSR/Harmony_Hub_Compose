package com.example.harmonyhub.features.artist.presentation.state

import com.example.harmonyhub.features.artist.data.remote.models.ArtistData

sealed class ArtistUiState() {
    data object Loading: ArtistUiState()
    data class Success(val data: ArtistData): ArtistUiState()
    data class Error(val message: String): ArtistUiState()


}