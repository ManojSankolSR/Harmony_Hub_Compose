package com.example.harmonyhub.features.serach.presentation.components.results

import androidx.compose.foundation.lazy.LazyListScope
import androidx.navigation.NavHostController
import com.example.harmonyhub.features.artist.presentation.components.SectionTitle
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.serach.data.remote.models.PlaylistSearchItem

fun LazyListScope.playlistsResultList(
    playlists: List<PlaylistSearchItem>,
    navController: NavHostController,
    musicPlayerViewModel: MusicPlayerViewModel
) {
    if (playlists.isNotEmpty()) {
        item {
            SectionTitle("Playlists")
            HorizontalResultList(
                items = playlists,
                navController = navController,
                musicPlayerViewModel = musicPlayerViewModel
            )
        }
    }
}
