package com.example.harmonyhub.features.music_player.presentation.components.player.expanded

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Forward10
import androidx.compose.material.icons.rounded.Replay10
import androidx.compose.material.icons.rounded.Shuffle
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarScrollBehavior
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.harmonyhub.features.local_palylist.presentation.components.AddToPlaylistButton
import com.example.harmonyhub.features.song_download.presentation.components.download.DownloadSongButton
import com.example.harmonyhub.features.local_palylist.presentation.viewmodel.LocalPlaylistViewModel
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song
import com.example.harmonyhub.features.song_download.presentation.viewmodel.DownloadsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomAppBar(
    modifier: Modifier = Modifier,
    viewModel: MusicPlayerViewModel,
    localPlaylistViewModel: LocalPlaylistViewModel,
    song: Song,
    downloadsViewModel: DownloadsViewModel,
    scrollBehavior: BottomAppBarScrollBehavior,
) {

    val iconSize = 26.dp;

    fun skipPrev(milliseconds: Long = -10_000) {
        viewModel.skip(milliseconds)

    }

    fun skipNext(milliseconds: Long = 10_000) {
        viewModel.skip(milliseconds)

    }

    fun shuffle() {
        viewModel.shuffle()
    }




    BottomAppBar(
        modifier,
        scrollBehavior = scrollBehavior
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            IconButton(onClick = ::skipPrev) {
                Icon(Icons.Rounded.Replay10, null, modifier = modifier.size(iconSize))
            }

            IconButton(onClick = ::skipNext) {
                Icon(Icons.Rounded.Forward10, null, modifier = modifier.size(iconSize))
            }

            IconButton(onClick = ::shuffle) {
                Icon(Icons.Rounded.Shuffle, null, modifier = modifier.size(iconSize))
            }

            IconButton({}){AddToPlaylistButton(song=song,localPlaylistViewModel=localPlaylistViewModel,iconSize=iconSize)}

            IconButton({}){DownloadSongButton(
                viewModel = downloadsViewModel, song = song, iconSize = iconSize
            )}
        }
    }
}
