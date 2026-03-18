package com.example.harmonyhub.features.local_palylist.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.harmonyhub.features.local_palylist.data.local.entity.PlaylistEntity
import com.example.harmonyhub.features.local_palylist.presentation.components.playlist.PlaylistImage
import com.example.harmonyhub.features.local_palylist.presentation.state.LocalPlaylistUiState
import com.example.harmonyhub.features.local_palylist.presentation.viewmodel.LocalPlaylistViewModel
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddToPlaylistBottomSheet(
    song: Song, viewModel: LocalPlaylistViewModel, onDismiss: () -> Unit
) {
    val playlistState by viewModel.playlistWithSongsState.collectAsState()
    var showCreateDialog by remember { mutableStateOf(false) }

    fun onCreatePlaylistPress() {
        showCreateDialog = true
    }

    fun onAddToPlaylistPress(playlist: PlaylistEntity) {

        viewModel.addSongToPlaylist(
            playlist, song
        )
        onDismiss()

    }

    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
        ) {
            Text(
                text = "Add to Playlist",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )

            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable (onClick = ::onCreatePlaylistPress)
                .padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(40.dp))
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "Create New Playlist", style = MaterialTheme.typography.bodyLarge)
            }

            HorizontalDivider()

            when (val state = playlistState) {
                is LocalPlaylistUiState.Success -> {
                    LazyColumn {
                        items(state.data) { playlistWithSongs ->
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onAddToPlaylistPress(playlistWithSongs.playlist)
                                }
                                .padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                                PlaylistImage(
                                    songs = playlistWithSongs.songs,
                                    modifier = Modifier
                                        .size(48.dp)
                                        .clip(RoundedCornerShape(8.dp)),
                                    iconSize = 24
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                                Column {
                                    Text(
                                        text = playlistWithSongs.playlist.name,
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                    Text(
                                        text = "${playlistWithSongs.songs.size} songs",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }
                }

                is LocalPlaylistUiState.Loading -> {
                    Text(text = "Loading playlists...", modifier = Modifier.padding(16.dp))
                }

                is LocalPlaylistUiState.Error -> {
                    Text(text = "Error: ${state.message}", modifier = Modifier.padding(16.dp))
                }
            }
        }
    }

    if (showCreateDialog) {
        CreatePlaylistDialog(onDismiss = { showCreateDialog = false }, onCreate = { name ->
            viewModel.createPlaylist(name)
            showCreateDialog = false
        })
    }
}

