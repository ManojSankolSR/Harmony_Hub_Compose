package com.example.harmonyhub.features.playlist.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.harmonyhub.HarmonyHub
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.playlist.presentation.components.PlaylistContent
import com.example.harmonyhub.features.playlist.presentation.viewmodel.PlaylistDetailsViewModel
import com.example.harmonyhub.features.playlist.presentation.viewmodel.PlaylistDetailsViewModelFactory
import com.example.harmonyhub.navigation.bottom_bar_nav.PlaylistDetailScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaylistScreen(
    parentPaddingValues: PaddingValues,
    navController: NavHostController,
    data: PlaylistDetailScreen,
    musicPlayerViewModel: MusicPlayerViewModel
) {

    val app = LocalContext.current.applicationContext as HarmonyHub;

    val repository = app.appContainer.playlistRepository;

    val playlistDetailsViewModel: PlaylistDetailsViewModel = viewModel(
        factory = PlaylistDetailsViewModelFactory(repository)
    )

    val state = playlistDetailsViewModel.state.collectAsState().value;


    LaunchedEffect(data.id) {
        playlistDetailsViewModel.getPlaylistDetails(data.id)
    }

    PlaylistContent(
        state = state,
        playlistDetailsViewModel = playlistDetailsViewModel,
        musicPlayerViewModel = musicPlayerViewModel,
        navController = navController,
        playListId = data.id,
        parentPaddingValues,
    )


}
