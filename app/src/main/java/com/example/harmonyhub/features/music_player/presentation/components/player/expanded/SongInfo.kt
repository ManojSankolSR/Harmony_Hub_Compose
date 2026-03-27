package com.example.harmonyhub.features.music_player.presentation.components.player.expanded

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.harmonyhub.features.like.presentation.components.LikeSongsButton
import com.example.harmonyhub.features.home.presentation.components.MusicItemImage
import com.example.harmonyhub.features.like.presentation.viewmodel.LikedSongsViewModel
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song

@Composable
fun SongInfo(
    song: Song,
    padding: PaddingValues,
    paddingHorizontal: PaddingValues,
    likedSongsViewModel: LikedSongsViewModel,
) {

    var imageColor by remember {
        mutableStateOf(Color.Transparent)
    }


    val getImageColor: (Color) -> Unit = {
        imageColor = it
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    0.2f to imageColor,
                    0.3f to imageColor.copy(alpha = .8f),
                    0.4f to imageColor.copy(alpha = .4f),
                    .5f to Color.Transparent
                )
            )
            .padding(paddingValues = PaddingValues(top = padding.calculateTopPadding())),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(42.dp)
    ) {
        MusicItemImage(
            song = song,
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth(.75f)
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(14.dp)),
            getImageColor = getImageColor
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(end = 12.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(paddingHorizontal)
                    .padding(4.dp)
                    .weight(1f)
            ) {
                Text(
                    song?.name ?: "",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W600),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    song?.subtitle ?: "",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.W400),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            IconButton({}) {
                LikeSongsButton(viewModel = likedSongsViewModel, song = song)
            }
        }
    }
}
