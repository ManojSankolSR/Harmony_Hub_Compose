package com.example.harmonyhub.features.serach.presentation.components.results

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.harmonyhub.features.home.presentation.components.MusicItemCard2
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.serach.data.remote.models.AlbumSearchItem
import com.example.harmonyhub.features.serach.data.remote.models.ArtistSearchItem
import com.example.harmonyhub.features.serach.data.remote.models.PlaylistSearchItem
import com.example.harmonyhub.features.serach.data.remote.models.toMusicDataItem

@Composable
fun <T> HorizontalResultList(
    items: List<T>,
    navController: NavHostController,
    musicPlayerViewModel: MusicPlayerViewModel
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 6.dp)
    ) {
        items(items) { item ->
            val dataItem = when (item) {
                is AlbumSearchItem -> item.toMusicDataItem()
                is ArtistSearchItem -> item.toMusicDataItem()
                is PlaylistSearchItem -> item.toMusicDataItem()
                else -> null
            }
            dataItem?.let {
                MusicItemCard2(
                    dataItem = it,
                    navController = navController,
                    musicPlayerViewModel = musicPlayerViewModel
                )
            }
        }
    }
}
