package com.example.harmonyhub.features.artist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.harmonyhub.features.artist.data.repository.ArtistRepository

class ArtistViewModelFactory(
    private val repository: ArtistRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ArtistViewModel::class.java)) {
            return ArtistViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel")
    }
}