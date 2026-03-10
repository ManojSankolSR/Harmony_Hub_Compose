package com.example.harmonyhub.features.serach.presentation.components.top_searches

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.harmonyhub.HarmonyHub
import com.example.harmonyhub.core.navigation.MusicItemNavigator
import com.example.harmonyhub.features.home.data.remote.models.MusicDataItem
import com.example.harmonyhub.features.home.data.remote.models.MusicItemType
import com.example.harmonyhub.features.home.data.remote.models.getImageUrl
import com.example.harmonyhub.features.home.presentation.components.MusicItemImage
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.serach.data.remote.models.toSong
import kotlinx.coroutines.launch

@Composable
fun TopSearchItem(
    dataItem: MusicDataItem,
    navController: NavHostController,
    musicPlayerViewModel: MusicPlayerViewModel,
) {

    val coroutineScope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(false) }
    val app = LocalContext.current.applicationContext as HarmonyHub
    val repository = app.appContainer.songRepository

    fun fetchAndPlaySong() {
        coroutineScope.launch {
            isLoading = true
            val songs = repository.getSongs(dataItem.id)
            musicPlayerViewModel.setMediaItems(songs, 0)
            musicPlayerViewModel.play()
            isLoading = false
        }
    }

    fun onClick() {
        dataItem.type?.let { type ->
            when (type) {
                MusicItemType.SONG -> {
                    fetchAndPlaySong()
                }
                else -> {
                    MusicItemNavigator.navigate(
                        type,
                        navController,
                        dataItem,
                        musicPlayerViewModel
                    )
                }
            }

        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
            .clickable { onClick() }
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        MusicItemImage(
            imageUrl = dataItem.getImageUrl(),
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(
                    if (dataItem.type == MusicItemType.ARTIST || dataItem.type == MusicItemType.RADIO || dataItem.type == MusicItemType.RADIO_STATION) {
                        CircleShape
                    } else {
                        RoundedCornerShape(12.dp)
                    }
                )
        )
        Column {
            Text(
                text = dataItem.name ?: "",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = (dataItem.subtitle ?: "").ifEmpty {
                    dataItem.type?.name?.lowercase()?.replaceFirstChar { it.uppercaseChar() } ?: ""
                },
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
