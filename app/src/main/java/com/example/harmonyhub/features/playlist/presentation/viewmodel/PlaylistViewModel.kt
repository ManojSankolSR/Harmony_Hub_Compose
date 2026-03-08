package com.example.harmonyhub.features.playlist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harmonyhub.features.playlist.data.respository.PlaylistRepository
import com.example.harmonyhub.features.playlist.presentation.state.PlaylistDetailsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlaylistDetailsViewModel(private val repository: PlaylistRepository) : ViewModel() {

    private val _uisState = MutableStateFlow<PlaylistDetailsUiState>(PlaylistDetailsUiState.Loading)

    val state = _uisState.asStateFlow();

    fun getPlaylistDetails(id: String) {
        viewModelScope.launch {
            _uisState.update {
                PlaylistDetailsUiState.Loading
            }
            try {
                val data = repository.getPlaylistDetails(id)
                _uisState.update {
                    PlaylistDetailsUiState.Success(data)
                }
            } catch (e: Exception) {
                _uisState.update {
                    PlaylistDetailsUiState.Error(e.message ?: "Some Error Occurred")
                }
            }
        }
    }
}
