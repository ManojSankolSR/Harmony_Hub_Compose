package com.example.harmonyhub.features.local_palylist.presentation.components.playlist_details

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.example.harmonyhub.features.local_palylist.presentation.state.LocalPlaylistUiState
import com.example.harmonyhub.features.local_palylist.presentation.viewmodel.LocalPlaylistViewModel

@Composable
fun DeleteIcon(
    localPlaylistViewModel: LocalPlaylistViewModel,
    navController: NavHostController,
    playlistId: Int
) {

    val playlistsState = localPlaylistViewModel.playlistWithSongsState.collectAsState().value


    var showDeleteDialog by remember { mutableStateOf(false) }

    val onDelete: () -> Unit = {
        showDeleteDialog = true
    }

    val onDismiss: () -> Unit = {
        showDeleteDialog = false
    }

    val onDeleteConfirmed: () -> Unit =
        {
            if (playlistsState is LocalPlaylistUiState.Success) {
                playlistsState.data.find { it.playlist.id == playlistId }?.playlist?.let {
                    localPlaylistViewModel.deletePlaylist(it)
                    navController.popBackStack()
                }
            }
            showDeleteDialog = false
        }




    FilledTonalIconButton(
        onClick = onDelete,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.error,
            contentColor = MaterialTheme.colorScheme.onError
        )
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete Playlist",
        )
    }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(text = "Delete Playlist") },
            text = { Text(text = "Deleting Playlist will result in loss of all songs in the playlist. Are you sure you want to delete this playlist?") },
            confirmButton = {
                TextButton(
                    onClick = onDeleteConfirmed,
                    colors = ButtonDefaults.textButtonColors(
//                        containerColor = MaterialTheme.colorScheme.errorContainer,
                        contentColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text("Cancel")
                }
            }
        )
    }
}