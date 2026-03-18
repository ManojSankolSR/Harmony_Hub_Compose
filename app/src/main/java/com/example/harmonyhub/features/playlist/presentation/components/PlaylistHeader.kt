package com.example.harmonyhub.features.playlist.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.harmonyhub.core.presentation.components.OutlineButton
import com.example.harmonyhub.features.home.presentation.components.MusicItemImage


@Composable
fun PlaylistHeader(
    image: String,
    title: String,
    subtitle: String,
    subtitleDes: List<String>?,
    paddingValues: PaddingValues
) {


    var imageColor by remember {
        mutableStateOf(Color.Transparent)
    }


    val getImageColor: (Color) -> Unit = {
        imageColor = it
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()

            .background(
                Brush.verticalGradient(
                    0.5f to imageColor,
                    0.6f to imageColor.copy(alpha = .8f),
                    0.7f to imageColor.copy(alpha = .4f),
                    .9f to Color.Transparent

                ))
            . padding(top = paddingValues.calculateTopPadding())
            .height(160.dp)
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            MusicItemImage(
                imageUrl = image,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.35f)
                    .clip(RoundedCornerShape(8.dp)),
                getImageColor = getImageColor

            )
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(
                    16.dp
                )
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(0.dp)) {
                    Text(
                        title,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        subtitle,
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.W400),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    subtitleDes?.take(3)?.forEach { subtitle ->
                        OutlineButton() {
                            Text(subtitle, style = MaterialTheme.typography.labelMedium)
                        }
                    }
                }

            }


        }


    }

}