package com.example.harmonyhub.features.playlist.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.harmonyhub.HarmonyHub
import com.example.harmonyhub.core.presentation.components.SongsListItem
import com.example.harmonyhub.features.like.presentation.viewmodel.LikedSongsViewModel
import com.example.harmonyhub.features.local_palylist.presentation.viewmodel.LocalPlaylistViewModel
import com.example.harmonyhub.features.local_palylist.presentation.viewmodel.LocalPlaylistViewModelFactory
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.PlaylistData
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song
import com.example.harmonyhub.features.song_download.presentation.viewmodel.DownloadsViewModel

@Composable
fun PlaylistSuccess(
    data: PlaylistData,
    musicPlayerViewModel: MusicPlayerViewModel,
    paddingValues: PaddingValues,
    parentPaddingValues: PaddingValues,
    downloadsViewModel: DownloadsViewModel,
    likedSongsViewModel: LikedSongsViewModel
) {
    val app = LocalContext.current.applicationContext as HarmonyHub

    val localPlaylistViewModel: LocalPlaylistViewModel = viewModel(
        factory = LocalPlaylistViewModelFactory(app.appContainer.localPlaylistRepository)
    )

    val onClick: (songs: List<Song>, index: Int) -> Unit = { songs, index ->
        musicPlayerViewModel.setMediaItems(songs = songs, index)
        musicPlayerViewModel.play()
        musicPlayerViewModel.fetchAndAddSongRecommendationsToQueue()
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(2.dp),
        contentPadding = PaddingValues(bottom = parentPaddingValues.calculateBottomPadding())
    ) {
        item {
            PlaylistHeader(
                image = data.image,
                title = data.name,
                subtitle = data.headerDesc,
                subtitleDes = data.subtitleDesc,
                paddingValues = paddingValues
            )
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Songs",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )

                data.songs?.size?.let {
                    Text(
                        text = "$it tracks",
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
            }
        }
        itemsIndexed(items = data.songs ?: emptyList()) { index, song ->
            SongsListItem(
                song = song,
                onClick = { onClick(data.songs!!, index) },
                viewModel = musicPlayerViewModel,
                localPlaylistViewModel = localPlaylistViewModel,
                downloadsViewModel = downloadsViewModel,
                likedSongsViewModel = likedSongsViewModel
            )
        }
    }
}
