package com.example.harmonyhub.features.auth.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Equalizer
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.harmonyhub.core.models.AudioQuality


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AudioQualitySelector(audioQuality: AudioQuality, setAudioQuality: (AudioQuality) -> Unit) {

    var isVisible by remember {
        mutableStateOf(false)
    }


    fun closeBottomSheet() {
        isVisible = false;
    }

    fun openBottomSheet() {
        isVisible = true;
    }

    AssistChip(
        label = { Text(audioQuality.displayName, style = MaterialTheme.typography.labelSmall) },
        onClick = ::openBottomSheet,
        trailingIcon = { Icon(Icons.Default.ArrowDropDown, contentDescription = "") }
    )

    if (isVisible)
        ModalBottomSheet(
            onDismissRequest = ::closeBottomSheet,
            containerColor = MaterialTheme.colorScheme.background
        ) {

            Column {
                Text(
                    text = "Audio Quality",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.W500
                    ),
                    modifier = Modifier.padding(16.dp)
                )
                AudioQuality.entries.forEach { quality ->
                    val isSelected = quality == audioQuality;
                    ElevatedCard(
                        colors = CardDefaults.cardColors(
                            containerColor =
                                if (isSelected)
                                    MaterialTheme.colorScheme.primaryContainer
                                else
                                    MaterialTheme.colorScheme.surfaceContainerLow
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                setAudioQuality(quality)
                                closeBottomSheet()
                            }
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .border(
                                width = if (isSelected) 1.dp else 0.dp,
                                shape = RoundedCornerShape(if (isSelected) 12.dp else 0.dp),
                                color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
                            ),
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                quality.displayName,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.W500
                            )
                            if (quality == audioQuality) {
                                Icon(

                                    Icons.Default.Check,
                                    contentDescription = "Selected",
                                    modifier = Modifier.size(16.dp),
                                )
                            }
                        }
                    }
                }
                Spacer(Modifier.height(20.dp))
            }
        }


}