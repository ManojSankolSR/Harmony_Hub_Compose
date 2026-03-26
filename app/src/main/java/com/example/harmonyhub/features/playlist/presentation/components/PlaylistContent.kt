package com.example.harmonyhub.features.playlist.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.harmonyhub.core.presentation.components.ErrorView
import com.example.harmonyhub.core.presentation.components.LoaderView
import com.example.harmonyhub.features.like.presentation.viewmodel.LikedSongsViewModel
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.playlist.presentation.state.PlaylistDetailsUiState
import com.example.harmonyhub.features.playlist.presentation.viewmodel.PlaylistDetailsViewModel
import com.example.harmonyhub.features.song_download.presentation.viewmodel.DownloadsViewModel


@Composable
fun PlaylistContent(
    state: PlaylistDetailsUiState,
    playlistDetailsViewModel: PlaylistDetailsViewModel,
    musicPlayerViewModel: MusicPlayerViewModel,
    navController: NavHostController,
    playListId: String,
    paddingValues: PaddingValues,
    downloadsViewModel: DownloadsViewModel,
    likedSongsViewModel: LikedSongsViewModel
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
                LoaderView(
                    PaddingValues(
                        top = padding.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding()

                    )
                )
            }

            is PlaylistDetailsUiState.Success -> {
                val playlistData = state.data;
                PlaylistSuccess(
                    playlistData,
                    musicPlayerViewModel,
                    padding,
                    paddingValues,
                    downloadsViewModel,
                    likedSongsViewModel = likedSongsViewModel
                )
            }

            is PlaylistDetailsUiState.Error -> {
                val message = state.message
                ErrorView(
                    onRefresh = ::onRefresh,
                    message,
                    paddingValues = PaddingValues(
                        top = padding.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding()

                    )
                )

            }
        }


    }
}