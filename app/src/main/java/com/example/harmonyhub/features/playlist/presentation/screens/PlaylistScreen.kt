package com.example.harmonyhub.features.playlist.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.harmonyhub.HarmonyHub
import com.example.harmonyhub.core.presentation.components.Loader
import com.example.harmonyhub.features.home.data.remote.models.MusicItemType
import com.example.harmonyhub.features.playlist.presentation.components.PlaylistHeader
import com.example.harmonyhub.features.playlist.presentation.components.SongsListItem
import com.example.harmonyhub.features.playlist.presentation.state.PlaylistDetailsUiState
import com.example.harmonyhub.features.playlist.presentation.viewmodel.PlaylistDetailsViewModel
import com.example.harmonyhub.features.playlist.presentation.viewmodel.PlaylistDetailsViewModelFactory
import com.example.harmonyhub.navigation.bottom_bar_nav.PlaylistDetailScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaylistScreen(navController: NavHostController, data: PlaylistDetailScreen) {

    val app = LocalContext.current.applicationContext as HarmonyHub;

    val repository = app.appContainer.playlistRepository;

    val viewModel: PlaylistDetailsViewModel = viewModel(
        factory = PlaylistDetailsViewModelFactory(repository)
    )

    val state = viewModel.state.collectAsState().value;

    LaunchedEffect(data.id) {
        viewModel.getPlaylistDetails(data.id)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }
                    ) {
                        Icon(Icons.Default.ArrowBack, null)
                    }
                })
        }
    ) { padding ->
        Box(Modifier.padding(padding)) {

            when (state) {
                is PlaylistDetailsUiState.Loading -> {
                    Loader(padding)
                }

                is PlaylistDetailsUiState.Success -> {
                    val playlistData = state.data.data;

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        contentPadding = PaddingValues(bottom = 160.dp)
                    ) {
                        item {
                            PlaylistHeader(
                                playlistData.image,
                                title = playlistData.name,
                                subtitle = playlistData.headerDesc,
                                subtitleDes = playlistData.subtitleDesc
                            )
                        }
                        item {
                            Text(
                                "Songs",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.W500,
                                    color = MaterialTheme.colorScheme.primary
                                ),
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                        items(items = playlistData.songs ?: emptyList()) { song ->
                            SongsListItem(song)
                        }
                    }

                }

                is PlaylistDetailsUiState.Error -> {
                    Text("Failure")

                }
            }


        }
    }
}
