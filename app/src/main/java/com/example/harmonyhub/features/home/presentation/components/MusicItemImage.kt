package com.example.harmonyhub.features.home.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.harmonyhub.R
import com.example.harmonyhub.features.home.data.remote.models.MusicItemType
import com.example.harmonyhub.features.home.data.remote.models.getImageUrl


@Composable
fun MusicItemImage(imageUrl: String? ,modifier: Modifier = Modifier) {

    AsyncImage(
        model =
            ImageRequest
                .Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
        error = painterResource(R.drawable.outline_image_24),
        placeholder = painterResource(R.drawable.outline_image_24),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = modifier,
        onError = {
            Log.d("MusicItemCard2", "onError: ${it.result.throwable}")
        }
    )
}