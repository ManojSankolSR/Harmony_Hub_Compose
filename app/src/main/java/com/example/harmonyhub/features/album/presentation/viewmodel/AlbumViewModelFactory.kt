package com.example.harmonyhub.features.album.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.harmonyhub.features.album.data.repository.AlbumRepository

class AlbumViewModelFactory(
    private val repository: AlbumRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(AlbumViewModel::class.java)) {
            return AlbumViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel")
    }
}