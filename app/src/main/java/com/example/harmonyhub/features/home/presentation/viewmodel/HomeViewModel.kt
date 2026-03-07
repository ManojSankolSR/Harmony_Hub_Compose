package com.example.harmonyhub.features.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.harmonyhub.features.home.data.respository.HomeRepository
import com.example.harmonyhub.features.home.presentation.state.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)

    val state=_uiState.asStateFlow();

    init {
        fetchHomeData()
    }

    fun fetchHomeData(){

        viewModelScope.launch {
            _uiState.update {
                HomeUiState.Loading
            }
            try {
                val homeData=repository.getHomeData();
                _uiState.update {
                    HomeUiState.Success(homeData)
                }
            } catch (e: Exception) {
                _uiState.update {
                    HomeUiState.Error(e.message ?: "An unexpected error occurred")
                }
            }
        }
    }
}

class HomeViewModelFactory(
    private val repository: HomeRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel")
    }
}
