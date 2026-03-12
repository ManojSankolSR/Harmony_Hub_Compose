package com.example.harmonyhub.features.song_download.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.harmonyhub.core.presentation.components.SongsListItem
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Downloads",
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when (val state = uiState) {
                is DownloadedSongsUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is DownloadedSongsUiState.Success -> {
                    if (state.songs.isEmpty()) {
                        Text(
                            "No downloaded songs found",
                            modifier = Modifier.align(Alignment.Center)
                        )
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(bottom = paddingValues.calculateBottomPadding()) // Space for mini player
                        ) {
                            items(state.songs) { song ->
                                SongsListItem(
                                    song = song,
                                    viewModel = musicPlayerViewModel,
                                    onClick = {
                                        musicPlayerViewModel.setMediaItems(state.songs, state.songs.indexOf(song))
                                        musicPlayerViewModel.play()
                                    }
                                )
                            }
                        }
                    }
                }

                is DownloadedSongsUiState.Error -> {
                    Text(
                        "Error: ${state.message}",
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}
