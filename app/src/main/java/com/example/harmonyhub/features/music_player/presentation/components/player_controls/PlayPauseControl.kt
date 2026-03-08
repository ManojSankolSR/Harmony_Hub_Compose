package com.example.harmonyhub.features.music_player.presentation.components.player_controls

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PauseCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.harmonyhub.features.music_player.presentation.state.PlaybackState
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel


@Composable
fun PlayPauseControl(viewModel: MusicPlayerViewModel, iconSize: Dp = 24.dp) {
    val playbackState = viewModel.playerState.collectAsState().value.playbackState

    fun onClick() {


        when (playbackState) {
            PlaybackState.Playing -> {
                viewModel.pause()
            }

            PlaybackState.Paused -> {
                viewModel.play()
            }

            PlaybackState.Completed-> {

                viewModel.replay()
            }

            PlaybackState.Error -> {

                viewModel.replay(true)
            }

            PlaybackState.Loading -> {}
        }

    }


    Box(
        Modifier
            .clip(RoundedCornerShape(50))
            .clickable(
                onClick = ::onClick
            )
            .padding(4.dp)
    ) {
        when (playbackState) {
            PlaybackState.Playing -> {
                Icon(
                    Icons.Default.PauseCircle, null, Modifier.size(iconSize),
                    tint = MaterialTheme.colorScheme.primary
                )

            }

            PlaybackState.Paused -> {
                Icon(
                    Icons.Default.PlayCircleFilled,
                    null,
                    Modifier.size(iconSize),
                    tint = MaterialTheme.colorScheme.primary
                )

            }

            PlaybackState.Completed, PlaybackState.Error -> {

                Icon(
                    Icons.Default.Replay,
                    null,
                    Modifier.size(iconSize),
                    tint = MaterialTheme.colorScheme.primary
                )

            }

            PlaybackState.Loading -> {

                CircularProgressIndicator(Modifier.size(iconSize))

            }
        }
    }


}