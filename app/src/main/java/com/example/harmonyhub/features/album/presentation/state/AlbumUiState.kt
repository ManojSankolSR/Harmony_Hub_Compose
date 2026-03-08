package com.example.harmonyhub.features.album.presentation.state

import com.example.harmonyhub.features.album.data.remote.models.AlbumData

sealed class AlbumUiState(){
    data object Loading: AlbumUiState()
    data class Success(val data: AlbumData): AlbumUiState()
    data class Error(val message: String): AlbumUiState()
}
