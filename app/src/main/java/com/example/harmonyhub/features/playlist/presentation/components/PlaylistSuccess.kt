package com.example.harmonyhub.features.playlist.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.PlaylistData
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song






@Composable
fun PlaylistSuccess(data: PlaylistData, musicPlayerViewModel: MusicPlayerViewModel) {

    val onClick: (songs: List<Song>, index: Int) -> Unit = { songs, index ->
        musicPlayerViewModel.setMediaItems(songs = songs, index)
        musicPlayerViewModel.play()
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(bottom = 160.dp)
    ) {
        item {
            PlaylistHeader(
                data.image,
                title = data.name,
                subtitle = data.headerDesc,
                subtitleDes = data.subtitleDesc
            )
        }
        item {
            Text(
                "Songs", style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.W500, color = MaterialTheme.colorScheme.primary
                ), modifier = Modifier.padding(8.dp)
            )
        }
        itemsIndexed(items = data.songs ?: emptyList()) { index, song ->
            SongsListItem(
                song,
                onClick = { onClick(data.songs!!, index) },
                viewModel = musicPlayerViewModel,
            )
        }
    }
}