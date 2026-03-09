package com.example.harmonyhub.features.music_player.presentation.components.player

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.harmonyhub.features.artist.presentation.components.SectionTitle
import com.example.harmonyhub.features.home.data.remote.models.ArtistMap
import com.example.harmonyhub.features.home.data.remote.models.toMusicDataItem
import com.example.harmonyhub.features.home.presentation.components.MusicItemCard2
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Artists


@Composable
fun ArtistList(
    artists: ArtistMap?,
    navController: NavHostController,
    musicPlayerViewModel: MusicPlayerViewModel,
    onArtistClick: () -> Unit
) {

    artists?.artists?.let {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,

            ) {
            items(artists.artists) {
                MusicItemCard2(
                    dataItem = it.toMusicDataItem(),
                    navController = navController,
                    musicPlayerViewModel = musicPlayerViewModel,
                    onPress = onArtistClick
                )
            }
        }
    }




}