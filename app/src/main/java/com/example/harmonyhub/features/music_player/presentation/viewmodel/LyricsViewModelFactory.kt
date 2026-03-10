package com.example.harmonyhub.features.music_player.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.harmonyhub.features.music_player.data.repository.LyricsRepository

class LyricsViewModelFactory(
    private val lyricsRepository: LyricsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LyricsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LyricsViewModel(lyricsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
