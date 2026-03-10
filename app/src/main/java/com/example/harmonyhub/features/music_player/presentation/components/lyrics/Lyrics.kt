package com.example.harmonyhub.features.music_player.presentation.components.lyrics

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.harmonyhub.features.music_player.presentation.state.LyricsUiState
import com.example.harmonyhub.features.music_player.presentation.viewmodel.LyricsViewModel
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel

@Composable
fun Lyrics(
    modifier: Modifier = Modifier,
    playerViewModel: MusicPlayerViewModel,
    lyricsViewModel: LyricsViewModel
) {
    val lyricsState by lyricsViewModel.state.collectAsStateWithLifecycle()

    val currentSongId = playerViewModel.playerState.collectAsStateWithLifecycle().value.currentMediaItem?.id

    LaunchedEffect(currentSongId) {
        playerViewModel.playerState.value.currentMediaItem?.let { song ->
            lyricsViewModel.getLyrics(song.id, song.url, song.name)
        }
    }

    Card (
        modifier = modifier
            .fillMaxWidth()
            .height(450.dp),
        shape = MaterialTheme.shapes.large
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            when (val state = lyricsState) {
                is LyricsUiState.Loading -> {
                    CircularProgressIndicator()
                }
                is LyricsUiState.Success -> {
                    LyricsContent(
                        lyrics = state.lyrics,
                        playerViewModel = playerViewModel
                    )
                }
                is LyricsUiState.Error -> {
                    Text(
                        text = state.message,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}
