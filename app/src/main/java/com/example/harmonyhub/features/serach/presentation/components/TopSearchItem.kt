package com.example.harmonyhub.features.serach.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.harmonyhub.features.home.data.remote.models.MusicDataItem
import com.example.harmonyhub.features.home.data.remote.models.MusicItemType
import com.example.harmonyhub.features.home.data.remote.models.getImageUrl
import com.example.harmonyhub.features.home.presentation.components.MusicItemImage

@Composable
fun TopSearchItem(
    dataItem: MusicDataItem,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.Companion
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
            .clickable { onClick() }
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        MusicItemImage(
            imageUrl = dataItem.getImageUrl(),
            modifier = Modifier.Companion
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(
                    if (dataItem.type == MusicItemType.ARTIST || dataItem.type == MusicItemType.RADIO || dataItem.type == MusicItemType.RADIO_STATION) {
                        CircleShape
                    } else {
                        androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
                    }
                )
        )
        Column {
            Text(
                text = dataItem.name ?: "",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Companion.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Companion.Ellipsis
            )
            Text(
                text = (dataItem.subtitle ?: "").ifEmpty {
                    dataItem.type?.name?.lowercase()?.replaceFirstChar { it.uppercaseChar() } ?: ""
                },
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Companion.Ellipsis
            )
        }
    }
}