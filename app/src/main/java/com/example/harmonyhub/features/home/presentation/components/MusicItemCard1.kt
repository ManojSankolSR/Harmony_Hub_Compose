package com.example.harmonyhub.features.home.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.harmonyhub.core.navigation.MusicItemNavigator
import com.example.harmonyhub.features.home.data.remote.models.MusicDataItem
import com.example.harmonyhub.features.home.data.remote.models.getImageUrl
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import kotlinx.coroutines.launch


@Composable
fun MusicItemCard1(
    data: MusicDataItem,
    navController: NavHostController,
    musicPlayerViewModel: MusicPlayerViewModel
) {

    val scope= rememberCoroutineScope()

    val onMusicItemClick: () -> Unit = {
        data.type?.let { type ->
            scope.launch {
                MusicItemNavigator.navigate(type, navController, data,musicPlayerViewModel)
            }

        }
    }

    ElevatedCard(
        Modifier
            .height(130.dp)
            .width(360.dp)
            .padding(horizontal = 8.dp, vertical = 12.dp)
            .clickable {
                onMusicItemClick()
            }
    ) {
        Row(
            Modifier
                .padding(0.dp)
                .fillMaxSize(), Arrangement.spacedBy(12.dp)
        ) {
            MusicItemImage(
                imageUrl = data.getImageUrl(),
                modifier = Modifier
                    .width(100.dp)
                    .fillMaxHeight()
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text(
                    data.name ?: "",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W600),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    data.subtitle ?: "",
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.W400),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Icon(
                    Icons.Default.PlayCircle, null, Modifier
                        .size(40.dp)
                        .align(Alignment.End)
                )
            }
        }
    }
}