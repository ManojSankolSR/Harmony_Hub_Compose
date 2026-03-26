package com.example.harmonyhub.features.like.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.harmonyhub.features.like.presentation.state.LikedSongsUIState
import com.example.harmonyhub.features.like.presentation.viewmodel.LikedSongsViewModel
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.song_download.presentation.viewmodel.DownloadsViewModel

@Composable
fun LikedSongsContent(
    state: LikedSongsUIState.Success,
    paddingValues: PaddingValues,
    parentPaddingValues: PaddingValues,
    musicPlayerViewModel: MusicPlayerViewModel,
    downloadsViewModel: DownloadsViewModel,
    likedSongsViewModel: LikedSongsViewModel
) {
    if (state.songs.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    PaddingValues(
                        top = paddingValues.calculateTopPadding(),
                        bottom = parentPaddingValues.calculateBottomPadding()
                    ),
                ),
            Alignment.Center
        ) {
            Text("No liked songs found")
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                bottom = parentPaddingValues.calculateBottomPadding(),
                top = paddingValues.calculateTopPadding()
            )
        ) {
            items(
                items = state.songs,
                key = { it.id }
            ) { song ->
                LikedSongItem(
                    song = song,
                    musicPlayerViewModel = musicPlayerViewModel,
                    likedSongsViewModel = likedSongsViewModel,
                    downloadsViewModel = downloadsViewModel,
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
