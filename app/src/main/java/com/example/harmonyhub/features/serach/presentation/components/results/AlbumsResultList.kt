package com.example.harmonyhub.features.serach.presentation.components.results

import androidx.compose.foundation.lazy.LazyListScope
import androidx.navigation.NavHostController
import com.example.harmonyhub.features.artist.presentation.components.SectionTitle
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.serach.data.remote.models.AlbumSearchItem

fun LazyListScope.albumsResultList(
    albums: List<AlbumSearchItem>,
    navController: NavHostController,
    musicPlayerViewModel: MusicPlayerViewModel
) {
    if (albums.isNotEmpty()) {
        item {
            SectionTitle("Albums")
            HorizontalResultList(
                items = albums,
                navController = navController,
                musicPlayerViewModel = musicPlayerViewModel
            )
        }
    }
}
