package com.example.harmonyhub.features.local_palylist.presentation.components.playlist_details

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.harmonyhub.features.local_palylist.presentation.viewmodel.LocalPlaylistViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBar(
    playlistId: Int,
    playlistName: String,
    navController: NavHostController,
    localPlaylistViewModel: LocalPlaylistViewModel
) {
    fun onBack() {
        navController.popBackStack()
    }
    TopAppBar(
        title = { Text("") },
        navigationIcon = {
            FilledTonalIconButton(onClick = ::onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        actions = {
            DeleteIcon(navController = navController, playlistId = playlistId, localPlaylistViewModel = localPlaylistViewModel)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            navigationIconContentColor = MaterialTheme.colorScheme.onSurface
        )
    )
}

