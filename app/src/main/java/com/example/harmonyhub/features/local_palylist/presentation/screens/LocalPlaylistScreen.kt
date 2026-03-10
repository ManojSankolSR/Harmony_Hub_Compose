package com.example.harmonyhub.features.local_palylist.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.harmonyhub.core.presentation.components.ErrorView
import com.example.harmonyhub.core.presentation.components.Loader
import com.example.harmonyhub.features.local_palylist.presentation.components.CreatePlaylistDialog
import com.example.harmonyhub.features.local_palylist.presentation.components.playlist.TopBar
import com.example.harmonyhub.features.local_palylist.presentation.state.LocalPlaylistUiState
import com.example.harmonyhub.features.local_palylist.presentation.viewmodel.LocalPlaylistViewModel
import com.example.harmonyhub.features.local_palylist.presentation.components.playlist.PlaylistSuccess


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocalPlaylistScreen(
    viewModel: LocalPlaylistViewModel,
    paddingValues: PaddingValues,
    navController: NavHostController
) {
    val state = viewModel.playlistWithSongsState.collectAsState().value;
    var showCreateDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {TopBar(navController)},
        floatingActionButton = {
            FloatingActionButton(onClick = { showCreateDialog = true }, modifier = Modifier.padding(paddingValues)) {
                Icon(Icons.Default.Add, contentDescription = "Create Playlist")
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when (state) {
                is LocalPlaylistUiState.Loading -> Loader(padding)
                is LocalPlaylistUiState.Error -> ErrorView(
                    onRefresh = { viewModel.observePlaylistWithSongs() },
                    message = state.message,
                    paddingValues = paddingValues
                )
                is LocalPlaylistUiState.Success -> {
                    val data=state.data;
                    PlaylistSuccess(data, paddingValues,navController)
                }
            }
        }
    }

    if (showCreateDialog) {
        CreatePlaylistDialog(
            onDismiss = { showCreateDialog = false },
            onCreate = { name ->
                viewModel.createPlaylist(name)
                showCreateDialog = false
            }
        )
    }
}

