package com.example.harmonyhub.features.music_player.presentation.components.player.expanded

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.harmonyhub.features.music_player.presentation.components.player_controls.PlayPauseControl
import com.example.harmonyhub.features.music_player.presentation.components.player_controls.ProgressSeekBar
import com.example.harmonyhub.features.music_player.presentation.components.player_controls.SeekNextControl
import com.example.harmonyhub.features.music_player.presentation.components.player_controls.SeekPrevControl
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel

@Composable
fun PlayerControls(
    viewModel: MusicPlayerViewModel,
    paddingHorizontal: PaddingValues
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(paddingHorizontal),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        ProgressSeekBar(viewModel)
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SeekPrevControl(viewModel, 42.dp)
            PlayPauseControl(viewModel, 52.dp)
            SeekNextControl(viewModel, 42.dp)
        }
    }
}
