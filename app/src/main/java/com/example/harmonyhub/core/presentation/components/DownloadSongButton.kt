package com.example.harmonyhub.core.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.outlined.ArrowDownward
import androidx.compose.material.icons.rounded.DownloadDone
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.example.harmonyhub.features.auth.presentation.components.AudioQualitySelector
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song
import com.example.harmonyhub.features.song_download.presentation.state.DownloadedSongsUiState
import com.example.harmonyhub.features.song_download.presentation.viewmodel.DownloadsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DownloadSongButton (
    modifier: Modifier= Modifier,
    viewModel: DownloadsViewModel,
    song: Song,
    iconSize: Dp = 32.dp,
){

    val songsState = viewModel.uiStateDownloadedSongs.collectAsState().value
    var showQualitySelector by remember { mutableStateOf(false) }
    var selectedQuality by remember { mutableStateOf(AudioQuality.high) }

    fun onDownload(quality: AudioQuality) {
        viewModel.downloadSong(song, quality)
        showQualitySelector = false
    }

    fun onRefresh(){
        viewModel.getDownloadedSongs()
    }


    if (showQualitySelector) {
        AlertDialog(
            onDismissRequest = { showQualitySelector = false },
            title = {
                Text(
                    text = "Select Audio Quality",
                    style = MaterialTheme.typography.titleLarge
                )
            },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AudioQualitySelector(
                        audioQuality = selectedQuality,
                        setAudioQuality = { selectedQuality = it }
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = { onDownload(selectedQuality) }
                ) {
                    Text("Download")
                }
            },
            dismissButton = {
                TextButton(onClick = { showQualitySelector = false }) {
                    Text("Cancel")
                }
            }
        )
    }


    when(songsState){
        is DownloadedSongsUiState.Loading -> {
            IconButton(onClick = ::onRefresh){
                CircularProgressIndicator(modifier.size(iconSize))
            }
        }
        is DownloadedSongsUiState.Error -> {
            IconButton(onClick = ::onRefresh){
                Icon(Icons.Default.ErrorOutline,null)
            }
        }
        is DownloadedSongsUiState.Success -> {

            val isDownloaded = songsState.songs.any { it.id == song.id }

            IconButton(
                onClick = {
                    if (!isDownloaded) {
                        showQualitySelector = true
                    }
                },
                enabled = !isDownloaded
            ) {
                Icon(
                    if (isDownloaded) {
                        Icons.Rounded.DownloadDone
                    } else {
                        Icons.Outlined.ArrowDownward
                    }, null, modifier = modifier.size(iconSize)
                )
            }
        }
    }
}
