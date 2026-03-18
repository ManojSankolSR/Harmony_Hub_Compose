package com.example.harmonyhub.features.home.presentation.components

import android.graphics.Bitmap
import android.graphics.Color as AndroidColor
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color as ComposeColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.palette.graphics.Palette
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.allowHardware
import coil3.request.crossfade
import coil3.toBitmap
import com.example.harmonyhub.R
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.getImageUrl


@Composable
fun MusicItemImage(
    modifier: Modifier = Modifier,
    song: Song? = null,
    imageUrl: String? = null,
    imageBitmap: Bitmap? = null,
    getImageColor:((ComposeColor) -> Unit)?=null
) {
    val imageData = song?.imageBitmap ?: song?.getImageUrl() ?: imageBitmap ?: imageUrl

    val onImageLoaded:(AsyncImagePainter.State.Success)->Unit={ result->
        val bitmap = result.result.image.toBitmap()
        bitmap.let {
            Palette.from(bitmap).generate { palette ->
                val dominantColor = palette?.getVibrantColor(AndroidColor.RED)
                if (dominantColor != null) {
                    val color= ComposeColor(dominantColor)
                    if(getImageColor!=null){
                        getImageColor(color)
                    }

                }
            }
        }
    }

    AsyncImage(
        model =
            ImageRequest
                .Builder(LocalContext.current)
                .data(imageData)
                .allowHardware(false)
                .diskCachePolicy(CachePolicy.ENABLED)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .crossfade(true)
                .build(),
        error = painterResource(R.drawable.placeholder),
        placeholder = painterResource(R.drawable.placeholder),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = modifier,
        onSuccess = onImageLoaded,
        onError = {
            Log.d("MusicItemImage", "onError: ${it.result.throwable}")
        }
    )
}
