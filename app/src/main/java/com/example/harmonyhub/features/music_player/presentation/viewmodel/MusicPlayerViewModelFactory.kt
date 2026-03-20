package com.example.harmonyhub.features.music_player.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.harmonyhub.core.data.respository.SongRepository
import com.example.harmonyhub.features.auth.data.respository.UserRepository
import com.example.harmonyhub.features.music_player.data.repository.PlayerRepository
import com.example.harmonyhub.features.radio.data.repository.RadioRepository

class MusicPlayerViewModelFactory(
    private val application: Application,
    private val repository: PlayerRepository,
    private val userRepository: UserRepository,
    private val radioRepository: RadioRepository,
    private val songRepository: SongRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MusicPlayerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MusicPlayerViewModel(application, repository,userRepository,radioRepository,songRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}