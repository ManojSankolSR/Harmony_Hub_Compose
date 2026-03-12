package com.example.harmonyhub.features.library.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.harmonyhub.features.library.presentation.components.LibraryCard
import com.example.harmonyhub.features.local_palylist.presentation.viewmodel.LocalPlaylistViewModel
import com.example.harmonyhub.navigation.bottom_bar_nav.library_nav.LibraryNavRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(
    paddingValues: PaddingValues,
    localPlaylistViewModel: LocalPlaylistViewModel,
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Library",
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
                    )
                }
            )
        }
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(12.dp,bottom = paddingValues.calculateBottomPadding()),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            item {
                LibraryCard(
                    title = "Playlists",
                    onClick = { navController.navigate(LibraryNavRoutes.LocalPlaylists) },
                )
            }
            item {
                LibraryCard(
                    title = "Downloads",
                    onClick = { navController.navigate(LibraryNavRoutes.DownloadedSongs) },
                )
            }
        }

    }
}