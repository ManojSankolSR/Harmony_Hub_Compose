package com.example.harmonyhub.features.local_palylist.presentation.components.playlist_details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.harmonyhub.features.local_palylist.presentation.viewmodel.LocalPlaylistViewModel
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song

@Composable
fun PlaylistDetailsSuccess(
    playlistId: Int,
    title: String,
    songs: List<Song>,
    paddingValues: PaddingValues,
    musicPlayerViewModel: MusicPlayerViewModel,
    localPlaylistViewModel: LocalPlaylistViewModel? = null
) {

    fun onClick(index: Int) {
        musicPlayerViewModel.setMediaItems(songs, index)
        musicPlayerViewModel.play()
        musicPlayerViewModel.fetchAndAddSongRecommendationsToQueue()
    }

    if (songs.isEmpty()) {
        Box(Modifier
            .fillMaxSize()
            .padding(paddingValues), contentAlignment = Alignment.Center) {
            Text("No songs in this playlist.")
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = paddingValues.calculateBottomPadding())
        ) {
            item {
                PlaylistDetailsHeader(songs, title)
            }
            itemsIndexed(songs, key = { _, song -> song.id }) { index, song ->
                DismissibleSongItem(
                    song = song,
                    musicPlayerViewModel = musicPlayerViewModel,
                    onRemove = {
                        localPlaylistViewModel?.deleteSongFromPlaylist(playlistId, song.id)
                    },
                    onClick = {
                        onClick(index)
                    }
                )
            }
        }
    }
}
