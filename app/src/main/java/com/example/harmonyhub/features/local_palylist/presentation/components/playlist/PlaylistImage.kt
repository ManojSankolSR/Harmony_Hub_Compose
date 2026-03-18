package com.example.harmonyhub.features.local_palylist.presentation.components.playlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.PlaylistPlay
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.harmonyhub.R
import com.example.harmonyhub.features.music_player.data.local.entities.SongEntity

@Composable
fun PlaylistImage(
    songs: List<SongEntity>,
    modifier: Modifier = Modifier,
    iconSize: Int = 84,
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.Center
    ) {
        val images = songs.mapNotNull { it.image }.filter { it.isNotBlank() }.take(4)

        when (images.size) {
            4 -> {
                Column(modifier = Modifier.fillMaxSize()) {
                    Row(modifier = Modifier.weight(1f)) {
                        PlaylistImageItem(images[0], Modifier.weight(1f))
                        PlaylistImageItem(images[1], Modifier.weight(1f))
                    }
                    Row(modifier = Modifier.weight(1f)) {
                        PlaylistImageItem(images[2], Modifier.weight(1f))
                        PlaylistImageItem(images[3], Modifier.weight(1f))
                    }
                }
            }

            3 -> {
                Row(modifier = Modifier.fillMaxSize()) {
                    PlaylistImageItem(images[0], Modifier.weight(1f))
                    Column(modifier = Modifier.weight(1f)) {
                        PlaylistImageItem(images[1], Modifier.weight(1f))
                        PlaylistImageItem(images[2], Modifier.weight(1f))
                    }
                }
            }

            2 -> {
                Row(modifier = Modifier.fillMaxSize()) {
                    PlaylistImageItem(images[0], Modifier.weight(1f))
                    PlaylistImageItem(images[1], Modifier.weight(1f))
                }
            }

            1 -> {
                PlaylistImageItem(images[0], Modifier.fillMaxSize())
            }

            else -> {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    contentAlignment = Alignment.Center
                )
                {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.PlaylistPlay,
                        contentDescription = null,
                        modifier = Modifier.size(iconSize.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Composable
private fun PlaylistImageItem(
    imageUrl: String?,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        modifier = modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
        error = painterResource(R.drawable.placeholder),
        placeholder = painterResource(R.drawable.placeholder),
    )
}
