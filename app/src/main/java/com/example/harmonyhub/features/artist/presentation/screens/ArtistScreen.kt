package com.example.harmonyhub.features.artist.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.harmonyhub.HarmonyHub
import com.example.harmonyhub.features.artist.presentation.viewmodel.ArtistViewModel
import com.example.harmonyhub.features.artist.presentation.viewmodel.ArtistViewModelFactory
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.navigation.bottom_bar_nav.ArtistDetailsScreen


@Composable
fun ArtistScreen(
    navController: NavHostController,
    data: ArtistDetailsScreen,
    musicPlayerViewModel: MusicPlayerViewModel
) {

val app= LocalContext.current.applicationContext as HarmonyHub;
    val repository=app.appContainer.artistRepository;

    val artistViewModel: ArtistViewModel= viewModel(
        factory = ArtistViewModelFactory(repository)
    )

    LaunchedEffect(data.id) {
        artistViewModel.getArtistDetails(data.id)
    }



}