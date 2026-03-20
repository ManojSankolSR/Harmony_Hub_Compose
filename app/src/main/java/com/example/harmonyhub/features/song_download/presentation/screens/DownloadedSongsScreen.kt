package com.example.harmonyhub.features.song_download.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.harmonyhub.core.presentation.components.ErrorView
import com.example.harmonyhub.core.presentation.components.LoaderView
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.song_download.presentation.components.DownloadedSongsSuccess
import com.example.harmonyhub.features.song_download.presentation.components.TopBar
import com.example.harmonyhub.features.song_download.presentation.state.DownloadedSongsUiState
import com.example.harmonyhub.features.song_download.presentation.viewmodel.DownloadsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DownloadedSongsScreen(
    viewModel: DownloadsViewModel,
    musicPlayerViewModel: MusicPlayerViewModel,
    onBackClick: () -> Unit,
    paddingValues: PaddingValues
) {
    val uiState = viewModel.uiStateDownloadedSongs.collectAsState().value

    fun onRefresh() {
        viewModel.getDownloadedSongs()
    }

    Scaffold(
        topBar = {
            TopBar(onBackClick)
        }
    ) { padding ->

            when (uiState) {
                is DownloadedSongsUiState.Loading -> {
                    LoaderView(padding = PaddingValues(
                        top = padding.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding()
                    ))
                }

                is DownloadedSongsUiState.Success -> {
                    DownloadedSongsSuccess(uiState, padding,paddingValues, musicPlayerViewModel, viewModel)
                }

                is DownloadedSongsUiState.Error -> {
                    ErrorView(
                        onRefresh = ::onRefresh,
                        message = uiState.message,
                        paddingValues =  PaddingValues(
                            top = padding.calculateTopPadding(),
                            bottom = paddingValues.calculateBottomPadding()
                        )
                    )
                }
            }

    }
}

