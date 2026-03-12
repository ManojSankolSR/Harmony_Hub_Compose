package com.example.harmonyhub.features.song_download.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song
import com.example.harmonyhub.features.song_download.data.repository.DownloadRepository
import com.example.harmonyhub.features.song_download.presentation.state.DownloadedSongsUiState
import com.example.harmonyhub.features.song_download.presentation.state.SongsDownloadUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DownloadsViewModel(private val downloadRepository: DownloadRepository): ViewModel() {

    private val _uiStateDownloadedSongs = MutableStateFlow<DownloadedSongsUiState>(DownloadedSongsUiState.Loading)
    val uiStateDownloadedSongs = _uiStateDownloadedSongs.asStateFlow()

    private val _uiStateSongsDownload = MutableStateFlow<SongsDownloadUIState?>(null)
    val uiStateSongsDownload = _uiStateSongsDownload.asStateFlow()

    init {
        getDownloadedSongs()
    }

    fun getDownloadedSongs() {
        viewModelScope.launch {
            try {
                _uiStateDownloadedSongs.update { DownloadedSongsUiState.Loading }
                val songs = downloadRepository.getDownloadedSongs()
                _uiStateDownloadedSongs.update {
                    DownloadedSongsUiState.Success(songs)
                }
            } catch (e: Exception) {
                _uiStateDownloadedSongs.update {
                    DownloadedSongsUiState.Error(e.message ?: "Unknown error")
                }
            }
        }
    }

    fun downloadSong(song: Song) {
        viewModelScope.launch {
            try {
                _uiStateSongsDownload.update { SongsDownloadUIState.Downloading(0.0) }
                downloadRepository.downloadSong(song)
                _uiStateSongsDownload.update { SongsDownloadUIState.Success }
                getDownloadedSongs() // Refresh list
            } catch (e: Exception) {
                _uiStateSongsDownload.update { SongsDownloadUIState.Error(e.message ?: "Download failed") }
            }
        }
    }
}
