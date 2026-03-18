package com.example.harmonyhub.features.settings.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HighQuality
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.harmonyhub.core.models.AudioQuality
import com.example.harmonyhub.features.auth.presentation.viewmodel.AuthViewModel
import com.example.harmonyhub.features.auth.presentation.components.AudioQualitySelector

@Composable
fun AudioQualityItem(authViewModel: AuthViewModel ){

    val authState by authViewModel.state.collectAsState()

    val user = authState.user ?: return

    val quality = user.preferredAudioQuality

    fun onQualityChange(quality: AudioQuality) {
        authViewModel.updateAudioQuality(quality)
    }


    ListItem(
        modifier = Modifier.Companion.clickable {},
        leadingContent = { Icon(Icons.Default.HighQuality, contentDescription = "Audio Quality") },
        trailingContent = {
            AudioQualitySelector(
                audioQuality = quality,
                setAudioQuality = ::onQualityChange
            )
        },
        headlineContent = {
            Text(
                "Audio Quality",
                style = MaterialTheme.typography.titleSmall
            )
        },
        supportingContent = {
            Text(
                "Higher quality provides better sound but consumes more data",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.W400
            )
        }
    )
}