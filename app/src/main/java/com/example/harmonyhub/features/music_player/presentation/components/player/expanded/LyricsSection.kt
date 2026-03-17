package com.example.harmonyhub.features.music_player.presentation.components.player.expanded

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.harmonyhub.features.artist.presentation.components.SectionTitle
import com.example.harmonyhub.features.music_player.presentation.components.lyrics.Lyrics
import com.example.harmonyhub.features.music_player.presentation.viewmodel.LyricsViewModel
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel

@Composable
fun LyricsSection(
    modifier: Modifier = Modifier,
    viewModel: MusicPlayerViewModel,
    lyricsViewModel: LyricsViewModel,
    paddingHorizontal: PaddingValues
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(paddingHorizontal),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        SectionTitle("Lyrics")
        Lyrics(modifier, viewModel, lyricsViewModel)
    }
}
