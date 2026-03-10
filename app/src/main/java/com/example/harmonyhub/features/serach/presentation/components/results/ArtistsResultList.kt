package com.example.harmonyhub.features.serach.presentation.components.results

import androidx.compose.foundation.lazy.LazyListScope
import androidx.navigation.NavHostController
import com.example.harmonyhub.features.artist.presentation.components.SectionTitle
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.serach.data.remote.models.ArtistSearchItem

fun LazyListScope.artistsResultList(
    artists: List<ArtistSearchItem>,
    navController: NavHostController,
    musicPlayerViewModel: MusicPlayerViewModel
) {
    if (artists.isNotEmpty()) {
        item {
            SectionTitle("Artists")
            HorizontalResultList(
                items = artists,
                navController = navController,
                musicPlayerViewModel = musicPlayerViewModel
            )
        }
    }
}
