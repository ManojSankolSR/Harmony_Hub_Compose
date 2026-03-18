package com.example.harmonyhub.features.artist.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.harmonyhub.HarmonyHub
import com.example.harmonyhub.core.presentation.components.ErrorView
import com.example.harmonyhub.core.presentation.components.Loader
import com.example.harmonyhub.features.artist.presentation.components.ArtistContent
import com.example.harmonyhub.features.artist.presentation.state.ArtistUiState
import com.example.harmonyhub.features.artist.presentation.viewmodel.ArtistViewModel
import com.example.harmonyhub.features.artist.presentation.viewmodel.ArtistViewModelFactory
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.navigation.bottom_bar_nav.ArtistDetailsScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistScreen(
    parentPaddingValues: PaddingValues,
    navController: NavHostController,
    data: ArtistDetailsScreen,
    musicPlayerViewModel: MusicPlayerViewModel
) {
    val app = LocalContext.current.applicationContext as HarmonyHub
    val repository = app.appContainer.artistRepository

    val artistViewModel: ArtistViewModel = viewModel(
        factory = ArtistViewModelFactory(repository)
    )

    val uiState by artistViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(data.id) {
        artistViewModel.getArtistDetails(data.id)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    FilledTonalIconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {
            when (val state = uiState) {
                is ArtistUiState.Loading -> Loader(PaddingValues(
                    top = paddingValues.calculateTopPadding(),
                    bottom = parentPaddingValues.calculateBottomPadding()
                ))
                is ArtistUiState.Error -> ErrorView(
                    message = state.message,
                    onRefresh = { artistViewModel.getArtistDetails(data.id) },
                    paddingValues = parentPaddingValues
                )
                is ArtistUiState.Success -> ArtistContent(
                    artistData = state.data,
                    navController = navController,
                    musicPlayerViewModel = musicPlayerViewModel,
                    paddingValues = parentPaddingValues
                )
            }
        }
    }
}

