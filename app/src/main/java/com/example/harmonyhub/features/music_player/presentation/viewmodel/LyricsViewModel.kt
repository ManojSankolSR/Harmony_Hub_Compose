package com.example.harmonyhub.features.music_player.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harmonyhub.features.music_player.data.repository.LyricsRepository
import com.example.harmonyhub.features.music_player.presentation.state.LyricsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LyricsViewModel(private val lyricsRepository: LyricsRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<LyricsUiState>(LyricsUiState.Loading)
    private var lastSongId: String? = null
    val state = _uiState.asStateFlow()

    fun getLyrics(id: String, link: String, track: String) {
        if(lastSongId==id) return;
        lastSongId=id;
        viewModelScope.launch {
            try {
                _uiState.update {
                    LyricsUiState.Loading
                }
                val lyrics = lyricsRepository.getLyrics(id, link, track)

                _uiState.update {
                    LyricsUiState.Success(lyrics)
                }

            } catch (e: Exception) {
                Log.d("LyricsViewModel", "getLyrics: $id $link $track ")
                _uiState.update {
                    LyricsUiState.Error(e.message ?: "No Lyrics Found")
                }
            }

        }
    }
}