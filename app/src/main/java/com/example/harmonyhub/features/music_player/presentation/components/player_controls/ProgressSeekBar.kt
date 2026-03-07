package com.example.harmonyhub.features.music_player.presentation.components.player_controls

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel


@Composable
fun ProgressSeekBar(viewModel: MusicPlayerViewModel) {
    fun onSliderValueChange(value: Float){
        viewModel.seekTo(value.toLong())
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Slider(
            value = 0.5f,
            onValueChange = ::onSliderValueChange,
            valueRange = 0f..100f,
            modifier = Modifier
                .fillMaxWidth()
        )
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(
                "01:11",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W600),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                "01:59",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W400),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

        }
    }

}