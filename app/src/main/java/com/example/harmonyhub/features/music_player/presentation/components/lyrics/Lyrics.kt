package com.example.harmonyhub.features.music_player.presentation.components.lyrics

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.harmonyhub.core.presentation.components.ErrorView
import com.example.harmonyhub.core.presentation.components.LoaderView
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


    val fetchLyrics: () -> Unit = {
        playerViewModel.playerState.value.currentMediaItem?.let { song ->
            lyricsViewModel.getLyrics(song.id, song.url, song.name)
        }
    }

    LaunchedEffect(currentSongId) {
        fetchLyrics()
    }



    Card (
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(),
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
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .height(450.dp)
                    ){
                        LoaderView(
                            backgroundColor = Color.Transparent
                        )
                    }
                }
                is LyricsUiState.Success -> {
                    LyricsContent(
                        lyrics = state.lyrics,
                        playerViewModel = playerViewModel
                    )
                }
                is LyricsUiState.Error -> {
                    ErrorView(
                        title = state.message,
                        message = "Something went wrong while loading lyrics.",
                        onRefresh = fetchLyrics,
                        buttonText = "Refresh",
                        backgroundColor = Color.Transparent
                    )
                }
            }
        }
    }
}
