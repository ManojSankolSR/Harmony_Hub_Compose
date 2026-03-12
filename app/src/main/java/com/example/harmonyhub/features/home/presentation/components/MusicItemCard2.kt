package com.example.harmonyhub.features.home.presentation.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.navigation.NavHostController
import com.example.harmonyhub.core.navigation.MusicItemNavigator
import com.example.harmonyhub.features.home.data.remote.models.MusicDataItem
import com.example.harmonyhub.features.home.data.remote.models.MusicItemType
import com.example.harmonyhub.features.home.data.remote.models.getImageUrl
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel


@Composable
fun MusicItemCard2(
    dataItem: MusicDataItem,
    navController: NavHostController,
    musicPlayerViewModel: MusicPlayerViewModel,
    onPress:()-> Unit={}
) {

    Log.d("MusicItemCard2", "dataItem: ${dataItem.name} ${dataItem.getImageUrl()}")

    val onMusicItemClick: () -> Unit = {
        dataItem.type?.let { type ->
            onPress()
            MusicItemNavigator.navigate(type, navController, dataItem,musicPlayerViewModel)
        }
    }

    Column(
        Modifier
            .width(155.dp)
            .height(200.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                onMusicItemClick()
            }
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        MusicItemImage(
            imageUrl = dataItem.getImageUrl(),
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .clip(
                    if (dataItem.type == MusicItemType.RADIO_STATION || dataItem.type == MusicItemType.RADIO || dataItem.type == MusicItemType.ARTIST) CircleShape else RoundedCornerShape(
                        8.dp
                    )
                ),
        )
        Column(Modifier.padding(horizontal = 4.dp)) {
            Text(
                dataItem.name ?: "",
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = (dataItem.subtitle ?: "").ifEmpty { dataItem.type?.name?.replaceFirstChar { it.uppercaseChar() } ?: "" },
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.W400),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

    }
}