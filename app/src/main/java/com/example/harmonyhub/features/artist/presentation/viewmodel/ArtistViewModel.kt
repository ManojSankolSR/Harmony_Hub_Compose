package com.example.harmonyhub.features.artist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harmonyhub.features.artist.data.repository.ArtistRepository
import com.example.harmonyhub.features.artist.presentation.state.ArtistUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ArtistViewModel(private  val artistRepository: ArtistRepository): ViewModel() {
    private val _uiState= MutableStateFlow<ArtistUiState>(ArtistUiState.Loading)

    val state=_uiState.asStateFlow();

    fun getArtistDetails(id: String){
        viewModelScope.launch {
            _uiState.update {
                ArtistUiState.Loading
            }
            try {
                val data=artistRepository.getArtistDetails(id)
                _uiState.update {
                    ArtistUiState.Success(data)
                }
            }catch (e: Exception){
                _uiState.update {
                    ArtistUiState.Error(e.message ?: "Some Error Occured")
                }
            }

        }
    }

}


