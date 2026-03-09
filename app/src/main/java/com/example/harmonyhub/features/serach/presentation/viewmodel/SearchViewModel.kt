package com.example.harmonyhub.features.serach.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harmonyhub.features.serach.data.respository.SearchRepository
import com.example.harmonyhub.features.serach.presentation.state.SearchUiState
import com.example.harmonyhub.features.serach.presentation.state.TopSearchUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(private val searchRepository: SearchRepository) : ViewModel() {
    private val _uiSearchState = MutableStateFlow<SearchUiState>(SearchUiState.Loading)
    private val _uiTopSearchesState = MutableStateFlow<TopSearchUiState>(TopSearchUiState.Loading)

    val searchState = _uiSearchState.asStateFlow()

    val topSearchesState = _uiTopSearchesState.asStateFlow()

    fun getTopSearches() {
        viewModelScope.launch {
            try {
                _uiTopSearchesState.update {
                    TopSearchUiState.Loading
                }
                val data = searchRepository.getTopSearches();
                _uiTopSearchesState.update {
                    TopSearchUiState.Success(data)
                }
            } catch (e: Exception) {
                Log.e("SearchViewModel", "getTopSearches: ${e.message}")
                _uiTopSearchesState.update {
                    TopSearchUiState.Error(e.message ?: "Something went wrong")
                }
            }

        }
    }


    fun search(query: String) {
        if (query.isEmpty()) {
            _uiSearchState.update { SearchUiState.Loading }
            return
        }
        viewModelScope.launch {
            try {
                _uiSearchState.update {
                    SearchUiState.Loading
                }
                val data = searchRepository.search(query)
                _uiSearchState.update {
                    SearchUiState.Success(data)
                }
            } catch (e: Exception) {
                _uiSearchState.update {
                    SearchUiState.Error(e.message ?: "Something went wrong")
                }
            }
        }
    }
}
