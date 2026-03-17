package com.example.harmonyhub.features.song_download.presentation.components

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.song_download.presentation.state.DownloadedSongsUiState
import com.example.harmonyhub.features.song_download.presentation.viewmodel.DownloadsViewModel

@Composable
 fun BoxScope.DownloadedSongsSuccess(
    state: DownloadedSongsUiState.Success,
    paddingValues: PaddingValues,
    musicPlayerViewModel: MusicPlayerViewModel,
    viewModel: DownloadsViewModel
) {
    if (state.songs.isEmpty()) {
        Text(
            "No downloaded songs found",
            modifier = Modifier.Companion.align(Alignment.Companion.Center)
        )
    } else {
        LazyColumn(
            modifier = Modifier.Companion.fillMaxSize(),
            contentPadding = PaddingValues(bottom = paddingValues.calculateBottomPadding()) // Space for mini player
        ) {
            items(
                items = state.songs,
                key = { it.id }
            ) { song ->
                DownloadedSongItem(
                    song = song,
                    musicPlayerViewModel = musicPlayerViewModel,
                    onDelete = { viewModel.deleteSong(it) },
                    onClick = {
                        musicPlayerViewModel.setMediaItems(
                            state.songs,
                            state.songs.indexOf(song)
                        )
                        musicPlayerViewModel.play()
                    }
                )
            }
        }
    }
}