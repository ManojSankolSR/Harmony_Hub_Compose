package com.example.harmonyhub.features.serach.presentation.state

import com.example.harmonyhub.features.serach.data.remote.models.SearchData


sealed class SearchUiState(){
    data object Loading : SearchUiState()
    data class Success(val data: SearchData) : SearchUiState()
    data class Error(val message: String) : SearchUiState()
}