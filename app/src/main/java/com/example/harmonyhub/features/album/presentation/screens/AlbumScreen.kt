package com.example.harmonyhub.features.album.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.harmonyhub.HarmonyHub
import com.example.harmonyhub.features.album.presentation.components.AlbumContent
import com.example.harmonyhub.features.album.presentation.viewmodel.AlbumViewModel
import com.example.harmonyhub.features.album.presentation.viewmodel.AlbumViewModelFactory
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.navigation.bottom_bar_nav.AlbumDetailsScreen

@Composable
fun AlbumScreen(
    parentPaddingValues: PaddingValues,
    navController: NavHostController,
    data: AlbumDetailsScreen,
    musicPlayerViewModel: MusicPlayerViewModel
) {

    val app = LocalContext.current.applicationContext as HarmonyHub;

    val repository = app.appContainer.albumRepository;

    val albumViewModel: AlbumViewModel = viewModel(
        factory = AlbumViewModelFactory(repository)
    )

    val state = albumViewModel.state.collectAsState().value;


    LaunchedEffect(data.id) {
        albumViewModel.getAlbumDetails(data.id)
    }

    AlbumContent(
        state = state,
        albumViewModel = albumViewModel,
        musicPlayerViewModel = musicPlayerViewModel,
        navController = navController,
        albumId = data.id,
        parentPaddingValues
    )


}
