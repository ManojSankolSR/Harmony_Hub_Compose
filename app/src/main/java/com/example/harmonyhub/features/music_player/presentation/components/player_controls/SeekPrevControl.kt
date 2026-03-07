package com.example.harmonyhub.features.music_player.presentation.components.player_controls

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel

@Composable
fun SeekPrevControl(viewModel: MusicPlayerViewModel, iconSize: Dp = 24.dp) {
    val enabled = viewModel.playerState.collectAsState().value.prevControlEnabled

    fun onClick() {
        if (enabled) viewModel.prevMediaItem();
    }


    Box(
        Modifier
            .clip(RoundedCornerShape(50))
            .clickable(
                onClick = ::onClick
            )
            .padding(4.dp)
    ) {
        Icon(
            Icons.Default.SkipPrevious, null, Modifier
                .size(iconSize)
                .alpha(
                    if (enabled) 1f else .3f
                ), tint = MaterialTheme.colorScheme.primary
        )
    }

}