package com.example.harmonyhub.features.local_palylist.presentation.components.playlist_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.harmonyhub.features.artist.data.remote.models.ArtistData
import com.example.harmonyhub.features.artist.data.remote.models.getImageUrl
import com.example.harmonyhub.features.home.presentation.components.MusicItemImage
import com.example.harmonyhub.features.local_palylist.data.local.entity.toLocalEntity
import com.example.harmonyhub.features.local_palylist.presentation.components.playlist.PlaylistImage
import com.example.harmonyhub.features.music_player.data.local.entities.toEntity
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song

@Composable
fun PlaylistDetailsHeader(songs: List<Song>,title: String) {
    Box(
        modifier = Modifier.Companion
            .fillMaxWidth()
            .height(400.dp)
    ) {
        PlaylistImage(songs.map { it.toLocalEntity() })
        Box(
            modifier = Modifier.Companion
                .fillMaxSize()
                .background(
                    Brush.Companion.verticalGradient(
                        colors = listOf(
                            Color.Companion.Transparent,
                            MaterialTheme.colorScheme.surface.copy(alpha = 0.8f),
                            MaterialTheme.colorScheme.surface
                        ),
                        startY = 400f
                    )
                )
        )
        Column(
            modifier = Modifier.Companion
                .align(Alignment.Companion.BottomStart)
                .padding(14.dp)
        ) {

                Text(
                    text = title,
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Companion.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Companion.Ellipsis
                )
            Text(
                text = "${songs.count()} Songs",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}