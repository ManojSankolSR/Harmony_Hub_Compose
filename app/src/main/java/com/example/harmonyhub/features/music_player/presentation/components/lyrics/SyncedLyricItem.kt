package com.example.harmonyhub.features.music_player.presentation.components.lyrics

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.harmonyhub.features.music_player.data.remote.models.SyncedLyric

@Composable
fun SyncedLyricItem(
    line: SyncedLyric,
    isActive: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val color by animateColorAsState(
        targetValue = if (isActive) {
            MaterialTheme.colorScheme.onSurface
        } else {
            MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
        },
        animationSpec = tween(durationMillis = 400),
        label = "lyricColor"
    )

    val scale by animateFloatAsState(
        targetValue = if (isActive) 1.05f else 1f,
        animationSpec = tween(durationMillis = 400),
        label = "lyricScale"
    )

    Text(
        text = line.text,
        style = MaterialTheme.typography.headlineSmall.copy(
            fontWeight = if (isActive) FontWeight.Bold else FontWeight.SemiBold,
            fontSize = if (isActive) 24.sp else 22.sp,
            lineHeight = 34.sp
        ),
        color = color,
        modifier = modifier
            .fillMaxWidth()
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .clickable(
                interactionSource = null,
                indication = null,
                onClick = onClick
            )
            .padding(vertical = 8.dp, horizontal = 16.dp),
        textAlign = TextAlign.Start
    )
}
