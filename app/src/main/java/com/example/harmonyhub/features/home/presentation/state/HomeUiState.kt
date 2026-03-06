package com.example.harmonyhub.features.home.presentation.state

import com.example.harmonyhub.features.home.data.remote.models.HomeResponse


//data class HomeUiState(val data: HomeResponse?, var isLoading: Boolean = true, val error: String?)


sealed interface HomeUiState {

    data object Loading : HomeUiState

    data class Success(
        val data: HomeResponse
    ) : HomeUiState

    data class Error(
        val message: String
    ) : HomeUiState
}