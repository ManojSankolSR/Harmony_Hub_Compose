package com.example.harmonyhub.core.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LibraryAdd
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.harmonyhub.features.local_palylist.presentation.components.AddToPlaylistBottomSheet
import com.example.harmonyhub.features.local_palylist.presentation.viewmodel.LocalPlaylistViewModel
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song

@Composable
fun AddToPlaylistButton(
    modifier: Modifier = Modifier,
    song: Song,
    localPlaylistViewModel: LocalPlaylistViewModel,
    iconSize: Dp= 32.dp,
) {

    var opened by rememberSaveable {
        mutableStateOf(false)
    }

    fun addToPlaylist() {
        opened = true
    }

    fun onDismiss() {
        opened = false;
    }


    IconButton(onClick = ::addToPlaylist) {
        Icon(Icons.Outlined.LibraryAdd, null, modifier = modifier.size(iconSize))
    }

    if (opened) {
        AddToPlaylistBottomSheet(
            song = song, viewModel = localPlaylistViewModel, onDismiss = ::onDismiss
        )

    }
}