package com.example.harmonyhub.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.GraphicEq
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.harmonyhub.features.home.presentation.components.MusicItemImage
import com.example.harmonyhub.features.local_palylist.presentation.viewmodel.LocalPlaylistViewModel
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song

@Composable
fun SongsListItem(
    song: Song,
    viewModel: MusicPlayerViewModel,
    onClick: () -> Unit,
    isSelected: Boolean = false,
    localPlaylistViewModel: LocalPlaylistViewModel? = null
) {


    Row(
        Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(8.dp, vertical = 4.dp)
            .clip(RoundedCornerShape(8.dp))
            .then(
                if (isSelected)
                    Modifier
                        .border(
                            1.dp,
                            MaterialTheme.colorScheme.primary,
                            RoundedCornerShape(8.dp)
                        )
                        .background(MaterialTheme.colorScheme.primaryContainer)
                else
                    Modifier
            )
            .clickable {
                onClick()
            },
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        MusicItemImage(
            song = song,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.15f)
        )
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.Center) {
            Text(
                song.name ?: "",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W600),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                song.subtitle ?: "",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W400),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )


        }

        localPlaylistViewModel?.let {
            AddToPlaylistButton(song = song, localPlaylistViewModel = it, iconSize = 24.dp)
        }

        if (isSelected) {
            IconButton(onClick = {})
            {
                Icon(Icons.Outlined.GraphicEq, null)
            }
        }


    }

}
