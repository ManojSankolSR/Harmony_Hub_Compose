package com.example.harmonyhub.features.home.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.harmonyhub.core.navigation.MusicItemNavigator
import com.example.harmonyhub.features.home.data.remote.models.MusicDataItem
import com.example.harmonyhub.features.home.data.remote.models.MusicItemType
import com.example.harmonyhub.features.home.data.remote.models.getImageUrl
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import kotlinx.coroutines.launch


@Composable
fun MusicItemCard2(
    dataItem: MusicDataItem,
    navController: NavHostController,
    musicPlayerViewModel: MusicPlayerViewModel,
    onPress: () -> Unit = {}
) {

    val scope = rememberCoroutineScope()

    val subtitleText = (dataItem.subtitle ?: "").ifEmpty {
        dataItem.type?.name?.lowercase()?.replaceFirstChar { it.uppercaseChar() } ?: ""
    }

    val onMusicItemClick: () -> Unit = {
        dataItem.type?.let { type ->
            onPress()
            scope.launch {
                MusicItemNavigator.navigate(type, navController, dataItem, musicPlayerViewModel)
            }
        }
    }

    val isCircular = dataItem.type == MusicItemType.RADIO_STATION ||
            dataItem.type == MusicItemType.RADIO ||
            dataItem.type == MusicItemType.ARTIST

    Column(
        modifier = Modifier
            .width(125.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onMusicItemClick() }
            .padding(8.dp)
    ) {
        MusicItemImage(
            imageUrl = dataItem.getImageUrl(),
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(if (isCircular) CircleShape else RoundedCornerShape(12.dp))
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = dataItem.name ?: "",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = subtitleText,
            style = MaterialTheme.typography.labelMedium.copy(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Normal
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
