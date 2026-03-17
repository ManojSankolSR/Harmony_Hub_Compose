package com.example.harmonyhub.features.music_player.presentation.components.player.expanded

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.harmonyhub.features.artist.presentation.components.SectionTitle
import com.example.harmonyhub.features.home.data.remote.models.toArtistMap
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.google.gson.JsonElement

@Composable
fun ArtistSection(
    artistMap: JsonElement?,
    navController: NavHostController,
    viewModel: MusicPlayerViewModel,
    onArtistClick: () -> Unit,
    paddingHorizontal: PaddingValues
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .padding(paddingHorizontal),
        ) {
            SectionTitle("Artists")
        }
        ArtistList(
            artistMap.toArtistMap(),
            navController = navController,
            musicPlayerViewModel = viewModel,
            onArtistClick = onArtistClick,
            paddingHorizontal = paddingHorizontal
        )
    }
}
