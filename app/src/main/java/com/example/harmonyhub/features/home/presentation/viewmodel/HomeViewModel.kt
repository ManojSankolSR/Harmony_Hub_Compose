package com.example.harmonyhub.features.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.harmonyhub.core.data.respository.UserRepository
import com.example.harmonyhub.core.models.Language
import com.example.harmonyhub.features.home.data.respository.HomeRepository
import com.example.harmonyhub.features.home.presentation.state.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HomeRepository, private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)

    val state = _uiState.asStateFlow();


    init {
        observeLanguageAndFetchHomeData()
    }


    fun observeLanguageAndFetchHomeData() {

        viewModelScope.launch {
            _uiState.update {
                HomeUiState.Loading
            }
            try {
                userRepository.getUser().map {
                    it?.preferredLanguage
                }.distinctUntilChanged().collectLatest { langs ->
                    val homeData = repository.getHomeData(langs ?: emptyList());
                    _uiState.update {
                        HomeUiState.Success(homeData)
                    }
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
    private val repository: HomeRepository, private val userRepository: UserRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository, userRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel")
    }
}
