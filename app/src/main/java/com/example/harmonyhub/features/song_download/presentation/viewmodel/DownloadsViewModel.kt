package com.example.harmonyhub.features.song_download.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harmonyhub.core.models.AudioQuality
import com.example.harmonyhub.core.models.SnackBar
import com.example.harmonyhub.core.services.SnackBarManager
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song
import com.example.harmonyhub.features.song_download.data.repository.DownloadRepository
import com.example.harmonyhub.features.song_download.presentation.state.DownloadedSongsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DownloadsViewModel(private val downloadRepository: DownloadRepository): ViewModel() {

    private val _uiStateDownloadedSongs = MutableStateFlow<DownloadedSongsUiState>(DownloadedSongsUiState.Loading)
    val uiStateDownloadedSongs = _uiStateDownloadedSongs.asStateFlow()

    private val _uiStateSongsDownload = MutableStateFlow<Map<String, Long>>(emptyMap())
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

    fun downloadSong(song: Song, quality: AudioQuality) {
        viewModelScope.launch {
            try {
                SnackBarManager.show(
                    SnackBar.InfoSnackBar(
                        title = "Download Started",
                        description = "Downloading ${song.name}"
                    )
                )
                _uiStateSongsDownload.update { it ->
                    it + (song.id to 0L)
                }
                downloadRepository.downloadSong(song, quality) { progress ->
                    _uiStateSongsDownload.update { it ->
                        it + (song.id to progress.toLong())
                    }
                }
                getDownloadedSongs()
                SnackBarManager.show(
                    SnackBar.SuccessSnackBar(
                        title = "Download Complete",
                        description = "${song.name} downloaded successfully"
                    )
                )
            } catch (e: Exception) {
                SnackBarManager.show(
                    SnackBar.ErrorSnackBar(
                        title = "Download Failed",
                        description = e.message ?: "Could not download ${song.name}"
                    )
                )
            } finally {
                _uiStateSongsDownload.update {
                    it - song.id
                }
            }
        }
    }

    fun deleteSong(song: Song) {
        viewModelScope.launch {
            try {
                downloadRepository.deleteSong(song)
                getDownloadedSongs() // Refresh list
                SnackBarManager.show(
                    SnackBar.SuccessSnackBar(
                        title = "Song Deleted",
                        description = "${song.name} removed from downloads"
                    )
                )
            } catch (e: Exception) {
                SnackBarManager.show(
                    SnackBar.ErrorSnackBar(
                        title = "Delete Failed",
                        description = e.message ?: "Could not delete ${song.name}"
                    )
                )
            }
        }
    }
}
