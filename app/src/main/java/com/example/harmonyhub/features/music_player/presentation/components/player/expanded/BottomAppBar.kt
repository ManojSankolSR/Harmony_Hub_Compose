package com.example.harmonyhub.features.music_player.presentation.components.player.expanded

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LibraryAdd
import androidx.compose.material.icons.rounded.ArrowDownward
import androidx.compose.material.icons.rounded.Forward10
import androidx.compose.material.icons.rounded.Replay10
import androidx.compose.material.icons.rounded.Shuffle
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.harmonyhub.features.local_palylist.presentation.components.AddToPlaylistBottomSheet
import com.example.harmonyhub.features.local_palylist.presentation.viewmodel.LocalPlaylistViewModel
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song


@Composable
fun BottomAppBar(
    modifier: Modifier = Modifier,
    viewModel: MusicPlayerViewModel,
    localPlaylistViewModel: LocalPlaylistViewModel,
    song: Song
) {

    val iconSize = 32.dp;
    val iconColor = MaterialTheme.colorScheme.primary

    var opened by rememberSaveable {
        mutableStateOf(false)
    }

    fun skipPrev(milliseconds: Long = -10_000) {
        viewModel.skip(milliseconds)

    }

    fun skipNext(milliseconds: Long = 10_000) {
        viewModel.skip(milliseconds)

    }

    fun shuffle() {
        viewModel.shuffle()
    }

    fun download() {
        viewModel.shuffle()
    }


    fun addToPlaylist() {
        opened = true

    }

    fun onDismiss() {
        opened = false;
    }


    BottomAppBar(
        modifier
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

            IconButton(onClick = ::addToPlaylist) {
                Icon(Icons.Outlined.LibraryAdd, null, modifier = modifier.size(iconSize))
            }

            IconButton(onClick = ::download) {
                Icon(Icons.Rounded.ArrowDownward, null, modifier = modifier.size(iconSize))
            }


        }

        if (opened) {
            AddToPlaylistBottomSheet(
                song = song,
                viewModel = localPlaylistViewModel,
                onDismiss = ::onDismiss
            )

        }

    }

}