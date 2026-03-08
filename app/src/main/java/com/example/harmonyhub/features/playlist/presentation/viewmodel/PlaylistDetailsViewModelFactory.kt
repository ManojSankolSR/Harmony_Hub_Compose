package com.example.harmonyhub.features.playlist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.harmonyhub.features.playlist.data.respository.PlaylistRepository

class PlaylistDetailsViewModelFactory(
    private val repository: PlaylistRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(PlaylistDetailsViewModel::class.java)) {
            return PlaylistDetailsViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel")
    }
}