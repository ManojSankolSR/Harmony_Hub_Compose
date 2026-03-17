package com.example.harmonyhub.features.song_download.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.harmonyhub.core.presentation.components.ErrorView
import com.example.harmonyhub.core.presentation.components.Loader
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
    val uiState by viewModel.uiStateDownloadedSongs.collectAsState()

    fun onRefresh() {
        viewModel.getDownloadedSongs()
    }

    Scaffold(
        topBar = {
            TopBar(onBackClick)
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when (val state = uiState) {
                is DownloadedSongsUiState.Loading -> {
                    Loader(padding = paddingValues)
                }

                is DownloadedSongsUiState.Success -> {
                    DownloadedSongsSuccess(state, paddingValues, musicPlayerViewModel, viewModel)
                }

                is DownloadedSongsUiState.Error -> {
                    ErrorView(
                        onRefresh = ::onRefresh,
                        message = state.message,
                        paddingValues = paddingValues
                    )
                }
            }
        }
    }
}

