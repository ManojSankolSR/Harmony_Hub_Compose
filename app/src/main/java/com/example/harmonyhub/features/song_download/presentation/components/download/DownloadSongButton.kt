package com.example.harmonyhub.features.song_download.presentation.components.download

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material.icons.rounded.DownloadDone
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.harmonyhub.core.models.AudioQuality
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song
import com.example.harmonyhub.features.song_download.presentation.state.DownloadedSongsUiState
import com.example.harmonyhub.features.song_download.presentation.viewmodel.DownloadsViewModel


@Composable
fun DownloadSongButton(
    modifier: Modifier = Modifier,
    viewModel: DownloadsViewModel,
    song: Song,
    iconSize: Dp = 32.dp,
) {

    val songsState by viewModel.uiStateDownloadedSongs.collectAsState()
    val downloadState by viewModel.uiStateSongsDownload.collectAsState()
    var showQualitySelector by remember { mutableStateOf(false) }

    val isDownloaded =
        if (songsState is DownloadedSongsUiState.Success) (songsState as DownloadedSongsUiState.Success).songs.any { it.id == song.id } else false

    val onDownloadClick: () -> Unit = {
        if (!isDownloaded) {
            showQualitySelector = true
        }
    }

    val onDownload: (AudioQuality) -> Unit =
        { quality ->
            viewModel.downloadSong(song, quality)
            showQualitySelector = false
        }

    val onDismiss:()-> Unit={ showQualitySelector = false }


    if (downloadState.containsKey(song.id)) {
        val progress = (downloadState[song.id] ?: 0L).toFloat() / 100f
        Box(contentAlignment = Alignment.Center, modifier = modifier.size(iconSize)) {
            CircularProgressIndicator(
                progress = { progress },
                modifier = Modifier.size(iconSize),
                strokeWidth = 2.dp,
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )
        }
    } else {
        IconButton(
            onClick = onDownloadClick,
            modifier = modifier.size(iconSize)
        ) {
            Icon(
                imageVector = if (isDownloaded) Icons.Rounded.DownloadDone else Icons.Outlined.Download,
                contentDescription = if (isDownloaded) "Downloaded" else "Download",
                tint = if (isDownloaded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(iconSize)
            )
        }
    }


    if (showQualitySelector) {
        DownloadQualityDialog(
            song = song,
            onDismiss = onDismiss,
            onDownload = onDownload
        )
    }
}
