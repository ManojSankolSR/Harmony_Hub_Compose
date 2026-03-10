package com.example.harmonyhub.features.local_palylist.presentation.state

import com.example.harmonyhub.features.local_palylist.data.local.entity.PlaylistWithSongs

sealed class LocalPlaylistUiState{
    data object Loading: LocalPlaylistUiState()
    data class Success(val data: List<PlaylistWithSongs>) : LocalPlaylistUiState()
    data class Error(val message: String) : LocalPlaylistUiState()
}
