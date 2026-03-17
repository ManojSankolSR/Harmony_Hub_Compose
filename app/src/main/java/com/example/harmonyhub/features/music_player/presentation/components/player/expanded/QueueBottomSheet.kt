package com.example.harmonyhub.features.music_player.presentation.components.player.expanded

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QueueMusic
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song
import com.example.harmonyhub.core.presentation.components.SongsListItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QueueBottomSheet(viewModel: MusicPlayerViewModel) {
    var visible by remember { mutableStateOf(false) }

    val playerState by viewModel.playerState.collectAsState();

    val mediaItems = playerState.mediaItemQueue

    val currentMediaItem=playerState.currentMediaItem


    val onClick: (songs: List<Song>, index: Int) -> Unit = { songs, index ->
        viewModel.setMediaItems(songs = songs, index)
        viewModel.play()
    }

    fun toggleVisibility() {
        visible = !visible;
    }


    FloatingActionButton(
        onClick = { toggleVisibility() }
    ) {
        Icon(Icons.Default.QueueMusic, null)
    }


    if (visible)

        ModalBottomSheet(onDismissRequest = { toggleVisibility() }) {
            LazyColumn(
                modifier = Modifier.fillMaxHeight(.4f)
            ) {
                item {
                    Text(
                        "In Queue",
                        style = MaterialTheme.typography.titleLarge.copy(MaterialTheme.colorScheme.primary),
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 20.dp)
                    )
                }
                itemsIndexed(mediaItems) { index, song ->

                    val isSelected=currentMediaItem?.id==song.id;
                    SongsListItem(song, viewModel,{ onClick(mediaItems, index)},isSelected)
                }
            }
        }
}