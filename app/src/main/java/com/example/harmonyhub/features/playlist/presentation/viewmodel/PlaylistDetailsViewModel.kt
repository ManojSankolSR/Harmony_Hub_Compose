package com.example.harmonyhub.features.playlist.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.harmonyhub.core.data.respository.UserRepository
import com.example.harmonyhub.core.presentation.viewmodel.AuthViewModel
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
        try {
            viewModelScope.launch {
                _uisState.update {
                    PlaylistDetailsUiState.Loading
                }
                val data = repository.getPlaylistDetails(id)
                Log.d("PlaylistDetailsViewModel", "getPlaylistDetails: $data")
                _uisState.update {
                    PlaylistDetailsUiState.Success(data)
                }
            }
        } catch (e: Exception) {
            _uisState.update {
                PlaylistDetailsUiState.Error(e.message ?: "Some Error Occured")
            }
        }

    }
}


class PlaylistDetailsViewModelFactory(
    private val repository: PlaylistRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(PlaylistDetailsViewModel::class.java)) {
            return PlaylistDetailsViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel")
    }
}