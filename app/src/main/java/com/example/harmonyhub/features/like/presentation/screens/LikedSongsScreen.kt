package com.example.harmonyhub.features.like.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.harmonyhub.core.presentation.components.ErrorView
import com.example.harmonyhub.core.presentation.components.LoaderView
import com.example.harmonyhub.features.like.presentation.components.LikedSongsContent
import com.example.harmonyhub.features.like.presentation.components.TopBar
import com.example.harmonyhub.features.like.presentation.state.LikedSongsUIState
import com.example.harmonyhub.features.like.presentation.viewmodel.LikedSongsViewModel
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.song_download.presentation.viewmodel.DownloadsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LikedSongsScreen(
    viewModel: LikedSongsViewModel,
    musicPlayerViewModel: MusicPlayerViewModel,
    downloadsViewModel: DownloadsViewModel,
    onBackClick: () -> Unit,
    paddingValues: PaddingValues
) {
    val uiState = viewModel.state.collectAsState().value

    Scaffold(
        topBar = {
            TopBar(onBackClick)
        }
    ) { padding ->
        when (uiState) {
            is LikedSongsUIState.Loading -> {
                LoaderView(
                    padding = PaddingValues(
                        top = padding.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding()
                    )
                )
            }

            is LikedSongsUIState.Success -> {
                LikedSongsContent(
                    state = uiState,
                    paddingValues = padding,
                    parentPaddingValues = paddingValues,
                    musicPlayerViewModel = musicPlayerViewModel,
                    downloadsViewModel = downloadsViewModel,
                    likedSongsViewModel = viewModel
                )
            }

            is LikedSongsUIState.Error -> {
                ErrorView(
                    onRefresh = { viewModel.observeLikedSongs() },
                    message = uiState.message,
                    paddingValues = PaddingValues(
                        top = padding.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding()
                    )
                )
            }
        }
    }
}
