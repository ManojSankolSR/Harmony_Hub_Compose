package com.example.harmonyhub.features.local_palylist.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.harmonyhub.core.presentation.components.ErrorView
import com.example.harmonyhub.core.presentation.components.Loader
import com.example.harmonyhub.features.local_palylist.presentation.components.playlist_details.PlaylistDetailsSuccess
import com.example.harmonyhub.features.local_palylist.presentation.components.playlist_details.TopBar
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
            TopBar(playlistName, navController)
        }
    ) { padding ->

        when (state) {
            is LocalSongsOfPlaylistUiState.Loading -> Loader(
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
                val data = state.data;
                PlaylistDetailsSuccess(
                    title = playlistName,
                    data,
                    PaddingValues(
                        top = padding.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding()
                    ),
                    musicPlayerViewModel
                )
            }
        }
    }

}

