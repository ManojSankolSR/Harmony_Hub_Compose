package com.example.harmonyhub.features.local_palylist.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.example.harmonyhub.core.presentation.components.ErrorView
import com.example.harmonyhub.core.presentation.components.LoaderView
import com.example.harmonyhub.features.local_palylist.presentation.components.playlist_details.PlaylistDetailsSuccess
import com.example.harmonyhub.features.local_palylist.presentation.components.playlist_details.TopBar
import com.example.harmonyhub.features.local_palylist.presentation.state.LocalPlaylistUiState
import com.example.harmonyhub.features.local_palylist.presentation.state.LocalSongsOfPlaylistUiState
import com.example.harmonyhub.features.local_palylist.presentation.viewmodel.LocalPlaylistViewModel
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocalPlaylistDetailsScreen(
    playlistId: Int,
    playlistName: String,
    localPlaylistViewModel: LocalPlaylistViewModel,
    musicPlayerViewModel: MusicPlayerViewModel,
    paddingValues: PaddingValues,
    navController: NavHostController,
) {
    val state = localPlaylistViewModel.songsOfPlaylistState.collectAsState().value


    LaunchedEffect(playlistId) {
        localPlaylistViewModel.observeSongsOfPlaylist(playlistId)
    }

    Scaffold(
        topBar = {
            TopBar(
                playlistName = playlistName,
                navController = navController,
                playlistId = playlistId,
                localPlaylistViewModel = localPlaylistViewModel
            )
        }
    ) { padding ->

        when (state) {
            is LocalSongsOfPlaylistUiState.Loading -> LoaderView(
                padding = PaddingValues(
                    top = padding.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()
                )
            )

            is LocalSongsOfPlaylistUiState.Error -> ErrorView(
                onRefresh = { localPlaylistViewModel.observeSongsOfPlaylist(playlistId) },
                message = state.message,
                paddingValues = PaddingValues(
                    top = padding.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()
                )
            )

            is LocalSongsOfPlaylistUiState.Success -> {
                PlaylistDetailsSuccess(
                    playlistId = playlistId,
                    title = playlistName,
                    songs = state.data,
                    paddingValues = PaddingValues(
                        top = padding.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding()
                    ),
                    musicPlayerViewModel = musicPlayerViewModel,
                    localPlaylistViewModel = localPlaylistViewModel
                )
            }
        }
    }

}
