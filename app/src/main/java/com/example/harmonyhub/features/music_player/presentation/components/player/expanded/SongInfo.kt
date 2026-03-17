package com.example.harmonyhub.features.music_player.presentation.components.player.expanded

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.harmonyhub.features.home.presentation.components.MusicItemImage
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song

@Composable
fun SongInfo(
    song: Song?,
    paddingHorizontal: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingHorizontal),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(22.dp)
    ) {
        MusicItemImage(
            song = song,
            modifier = Modifier
                .height(350.dp)
                .clip(RoundedCornerShape(14.dp))
        )
        Column {
            Text(
                song?.name ?: "",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W600),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                song?.subtitle ?: "",
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.W400),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
