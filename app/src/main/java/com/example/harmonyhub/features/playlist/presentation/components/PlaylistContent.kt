package com.example.harmonyhub.features.playlist.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.harmonyhub.core.presentation.components.ErrorView
import com.example.harmonyhub.core.presentation.components.Loader
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.playlist.presentation.state.PlaylistDetailsUiState
import com.example.harmonyhub.features.playlist.presentation.viewmodel.PlaylistDetailsViewModel


@Composable
fun PlaylistContent(
    state: PlaylistDetailsUiState,
    playlistDetailsViewModel: PlaylistDetailsViewModel,
    musicPlayerViewModel: MusicPlayerViewModel,
    navController: NavHostController,
    playListId: String,
    paddingValues: PaddingValues
) {

    fun onRefresh() {
        playlistDetailsViewModel.getPlaylistDetails(playListId)
    }

    Scaffold(
        topBar = {
            TopBar(navController)
        }) { padding ->

            when (state) {
                is PlaylistDetailsUiState.Loading -> {
                    Loader(
                        PaddingValues(
                            top = padding.calculateTopPadding(),
                            bottom = paddingValues.calculateBottomPadding()

                        )
                    )
                }

                is PlaylistDetailsUiState.Success -> {
                    val playlistData = state.data;
                    PlaylistSuccess(playlistData, musicPlayerViewModel,   padding,paddingValues)
                }

                is PlaylistDetailsUiState.Error -> {
                    val message = state.message
                    ErrorView(
                        onRefresh = ::onRefresh,
                        message,
                        paddingValues =PaddingValues(
                            top = padding.calculateTopPadding(),
                            bottom = paddingValues.calculateBottomPadding()

                        )
                    )

                }
            }



    }
}