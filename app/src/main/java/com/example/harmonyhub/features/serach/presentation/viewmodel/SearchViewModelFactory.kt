package com.example.harmonyhub.features.serach.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.harmonyhub.features.serach.data.respository.SearchRepository

class SearchViewModelFactory(private val searchRepository: SearchRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchViewModel(searchRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
