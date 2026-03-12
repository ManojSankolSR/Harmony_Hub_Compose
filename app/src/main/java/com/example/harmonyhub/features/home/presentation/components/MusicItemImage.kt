package com.example.harmonyhub.features.home.presentation.components

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.harmonyhub.R
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.getImageUrl


@Composable
fun MusicItemImage(
    modifier: Modifier = Modifier,
    song: Song? = null,
    imageUrl: String? = null,
    imageBitmap: Bitmap? = null
) {
    val imageData = song?.imageBitmap ?: song?.getImageUrl() ?: imageBitmap ?: imageUrl

    AsyncImage(
        model =
            ImageRequest
                .Builder(LocalContext.current)
                .data(imageData)
                .diskCachePolicy(CachePolicy.ENABLED)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .crossfade(true)
                .build(),
        error = painterResource(R.drawable.outline_image_24),
        placeholder = painterResource(R.drawable.outline_image_24),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = modifier,
        onError = {
            Log.d("MusicItemImage", "onError: ${it.result.throwable}")
        }
    )
}
