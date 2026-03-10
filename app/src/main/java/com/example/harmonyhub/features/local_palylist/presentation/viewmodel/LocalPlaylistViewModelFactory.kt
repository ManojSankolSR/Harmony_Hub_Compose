package com.example.harmonyhub.features.local_palylist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.harmonyhub.features.local_palylist.data.repository.LocalPlaylistRepository

class LocalPlaylistViewModelFactory(private val repository: LocalPlaylistRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocalPlaylistViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LocalPlaylistViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
