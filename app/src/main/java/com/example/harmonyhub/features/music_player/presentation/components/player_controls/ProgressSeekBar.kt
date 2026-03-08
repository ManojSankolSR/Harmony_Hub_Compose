package com.example.harmonyhub.features.music_player.presentation.components.player_controls

import android.util.Log
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.DpSize

fun formatTime(ms: Long): String {

    if (ms <= 0) return "00:00"

    val totalSeconds = ms / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60

    return "%02d:%02d".format(minutes, seconds)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressSeekBar(viewModel: MusicPlayerViewModel, forMiniPlayer: Boolean = false) {

    val playerState by viewModel.playerState.collectAsState()

    val currentPosition = playerState.currentPosition

    val duration = playerState.duration

    var sliderPosition by remember {
        mutableFloatStateOf(0f)
    }

    Log.d("duration32343234 12", "$duration")


    fun onSliderValueChange(value: Float) {
        sliderPosition = value

    }

    fun onSliderValueChangFinished() {
        viewModel.seekTo(sliderPosition.toLong())

    }

    LaunchedEffect(currentPosition) {
        sliderPosition = currentPosition.toFloat()
    }


    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        if (!forMiniPlayer) {
            Slider(
                value = currentPosition.coerceIn(0, duration.coerceAtLeast(1)).toFloat(),
                onValueChange = ::onSliderValueChange,
                onValueChangeFinished = ::onSliderValueChangFinished,
                valueRange = 0f..duration.coerceAtLeast(1).toFloat(),
                modifier = Modifier.fillMaxWidth(),
                track = { sliderState ->
                    SliderDefaults.Track(
                        sliderState = sliderState, modifier = Modifier.height(10.dp) // thinner track
                    )
                },
                thumb = {
                    SliderDefaults.Thumb(
                        thumbSize = DpSize(6.dp,26.dp),
                        interactionSource = MutableInteractionSource()
                    )

                }

                )
        } else {

            Slider(
                value = currentPosition.coerceIn(0, duration.coerceAtLeast(1)).toFloat(),
                onValueChange = ::onSliderValueChange,
                onValueChangeFinished = ::onSliderValueChangFinished,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(3.dp)
                    .clip(RoundedCornerShape(0.dp)),
                steps = 0,
                track = { sliderState ->
                    SliderDefaults.Track(
                        sliderState = sliderState, modifier = Modifier.height(3.dp) // thinner track
                    )
                },
                valueRange = 0f..duration.coerceAtLeast(1).toFloat(),
                thumb = {},

                )

        }

        if (!forMiniPlayer) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    formatTime(currentPosition),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W600),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    formatTime(duration),
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W400),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

            }
        }
    }


}