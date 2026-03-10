package com.example.harmonyhub.features.local_palylist.presentation.components.playlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.harmonyhub.features.local_palylist.data.local.entity.PlaylistWithSongs
import com.example.harmonyhub.navigation.bottom_bar_nav.library_nav.LibraryNavRoutes

@Composable
fun PlaylistSuccess(
    playlists: List<PlaylistWithSongs>,
    paddingValues: PaddingValues,
    navController: NavHostController
) {

    fun onClick(id: Int, name: String) {
        navController.navigate(LibraryNavRoutes.LocalPlaylistDetails(id, name))
    }

    if (playlists.isEmpty()) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Companion.Center) {
            Text("No playlists yet. Create one!")
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(
                start = 12.dp,
                end = 12.dp,
                top = 12.dp,
                bottom = paddingValues.calculateBottomPadding() + 12.dp
            )
        ) {
            items(playlists) { playlistWithSongs ->
                val data = playlistWithSongs.playlist;
                PlaylistCard(
                    playlistWithSongs = playlistWithSongs,
                    onClick = {
                        onClick(data.id, data.name)
                    }
                )
            }
        }
    }
}