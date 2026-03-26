package com.example.harmonyhub.features.like.presentation.viewmodel

import com.example.harmonyhub.features.auth.presentation.viewmodel.AuthViewModel



import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.harmonyhub.features.auth.data.respository.UserRepository
import com.example.harmonyhub.features.like.data.repository.LikedSongsRepository

class LikedSongsViewModelFactory(
    private val repository: LikedSongsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(LikedSongsViewModel::class.java)) {
            return LikedSongsViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel")
    }
}