package com.example.harmonyhub.features.artist.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.harmonyhub.features.album.data.remote.models.AlbumData
import com.example.harmonyhub.features.home.data.remote.models.MusicDataItem
import com.example.harmonyhub.features.home.data.remote.models.MusicItemType
import com.example.harmonyhub.features.home.presentation.components.MusicItemCard2
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel

@Composable
fun HorizontalAlbumList(
    albums: List<AlbumData>,
    navController: NavHostController,
    musicPlayerViewModel: MusicPlayerViewModel
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 6.dp),
    ) {
        items(albums) { album ->
            MusicItemCard2(
                dataItem = MusicDataItem(
                    id = album.id,
                    name = album.name,
                    subtitle = album.subtitle,
                    type = MusicItemType.ALBUM,
                    image = album.image
                ),
                navController = navController,
                musicPlayerViewModel = musicPlayerViewModel
            )
        }
    }
}