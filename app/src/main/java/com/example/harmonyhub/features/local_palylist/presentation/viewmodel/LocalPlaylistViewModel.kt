package com.example.harmonyhub.features.local_palylist.presentation.viewmodel

import StackedSnackbarDuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harmonyhub.core.models.SnackBar
import com.example.harmonyhub.core.services.SnackBarManager
import com.example.harmonyhub.features.local_palylist.data.local.entity.PlaylistEntity
import com.example.harmonyhub.features.local_palylist.data.repository.LocalPlaylistRepository
import com.example.harmonyhub.features.local_palylist.presentation.state.LocalPlaylistUiState
import com.example.harmonyhub.features.local_palylist.presentation.state.LocalSongsOfPlaylistUiState
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LocalPlaylistViewModel(private val repository: LocalPlaylistRepository) : ViewModel() {

    private val _uiPlaylistWithSongsState = MutableStateFlow<LocalPlaylistUiState>(LocalPlaylistUiState.Loading)
    private val _uiSongsOfPlaylistState = MutableStateFlow<LocalSongsOfPlaylistUiState>(LocalSongsOfPlaylistUiState.Loading)

    val playlistWithSongsState = _uiPlaylistWithSongsState.asStateFlow()
    val songsOfPlaylistState = _uiSongsOfPlaylistState.asStateFlow()

    init {
        observePlaylistWithSongs()
    }

    fun observePlaylistWithSongs() {
        viewModelScope.launch {
            try {
                _uiPlaylistWithSongsState.update { LocalPlaylistUiState.Loading }
                repository.observePlaylistWithSongs().collect { playlistWithSongs ->
                    _uiPlaylistWithSongsState.update {
                        LocalPlaylistUiState.Success(playlistWithSongs)
                    }
                }
            } catch (e: Exception) {
                _uiPlaylistWithSongsState.update {
                    LocalPlaylistUiState.Error(e.message ?: "Unknown error")
                }
            }
        }
    }

    fun observeSongsOfPlaylist(playlistId: Int) {
        viewModelScope.launch {
            try {
                _uiSongsOfPlaylistState.update { LocalSongsOfPlaylistUiState.Loading }
                repository.observeSongsOfPlaylist(playlistId).collect { songEntities ->
                    _uiSongsOfPlaylistState.update {
                        LocalSongsOfPlaylistUiState.Success(songEntities)
                    }
                }
            } catch (e: Exception) {
                _uiSongsOfPlaylistState.update {
                    LocalSongsOfPlaylistUiState.Error(e.message ?: "Unknown error")
                }
            }
        }
    }

    fun createPlaylist(name: String) {
        viewModelScope.launch {
            repository.addPlaylist(name)
            SnackBarManager.show(
                SnackBar.SuccessSnackBar(
                    title = "Playlist Created",
                    description = "Playlist '$name' has been created successfully",
                    duration = StackedSnackbarDuration.Short
                )
            )
        }
    }

    fun addSongToPlaylist(playlist: PlaylistEntity, song: Song) {
        viewModelScope.launch {
            repository.addSongToPlaylist(playlist.id, song)
            SnackBarManager.show(
                SnackBar.SuccessSnackBar(
                    title = "Song Added",
                    description = "'${song.name}' has been added to '${playlist.name}' successfully",
                    duration = StackedSnackbarDuration.Short
                )
            )
        }
    }

    fun deletePlaylist(playlist: PlaylistEntity) {
        viewModelScope.launch {
            repository.deletePlaylist(playlist)
            SnackBarManager.show(
                SnackBar.SuccessSnackBar(
                    title = "Playlist Deleted",
                    description = "Playlist '${playlist.name}' has been deleted",
                    duration = StackedSnackbarDuration.Short
                )
            )
        }
    }

    fun deleteSongFromPlaylist(playlistId: Int, songId: String) {
        viewModelScope.launch {
            repository.deleteSongFromPlaylist(playlistId, songId)
            SnackBarManager.show(
                SnackBar.SuccessSnackBar(
                    title = "Song Removed",
                    description = "Song removed from playlist",
                    duration = StackedSnackbarDuration.Short
                )
            )
        }
    }
}
