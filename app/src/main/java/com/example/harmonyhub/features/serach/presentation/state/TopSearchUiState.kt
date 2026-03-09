package com.example.harmonyhub.features.serach.presentation.state

import com.example.harmonyhub.features.home.data.remote.models.MusicDataItem

sealed class TopSearchUiState {
    data object Loading : TopSearchUiState()
    data class Success(val data: List<MusicDataItem>) : TopSearchUiState()
    data class Error(val message: String) : TopSearchUiState()
}
