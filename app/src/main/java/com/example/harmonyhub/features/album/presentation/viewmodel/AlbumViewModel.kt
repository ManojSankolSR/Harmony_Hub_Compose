package com.example.harmonyhub.features.album.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harmonyhub.features.album.data.repository.AlbumRepository
import com.example.harmonyhub.features.album.presentation.state.AlbumUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AlbumViewModel(private val albumRepository: AlbumRepository) : ViewModel() {

    private val _uiState= MutableStateFlow<AlbumUiState>(AlbumUiState.Loading)

    val state = _uiState.asStateFlow()


    fun getAlbumDetails(id: String){
        viewModelScope.launch{
            _uiState.update {
                AlbumUiState.Loading
            }
            try {
                val albumData=albumRepository.getAlbumDetails(id)
                _uiState.update {
                    AlbumUiState.Success(albumData)
                }

            }catch (e: Exception){
                _uiState.update {
                    AlbumUiState.Error(e.message ?: "Unknown Error")
                }

            }
        }
    }


}


