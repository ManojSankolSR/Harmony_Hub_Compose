package com.example.harmonyhub.features.playlist.data.remote.models.playlist

import android.util.Log
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import coil3.Uri
import androidx.core.net.toUri

data class Song(
    val vlink: String = "",
    val year: Int = 0,
    val origin: String = "",
    val language: String = "",
    val copyrightText: String = "",
    val type: String = "",
    val Kbps: Boolean = false,
    val headerDesc: String = "",
    val vcode: String = "",
    val duration: Int = 0,
    val music: String = "",
    val starred: Boolean = false,
    val trillerAvailable: Boolean = false,
    val rights: Rights,
    val downloadUrl: List<DownloadUrlItem>?,
    val id: String = "",
    val image: Any?,
    val isDolbyContent: Boolean = false,
    val listCount: Int = 0,
    val album: String = "",
    val lyricsSnippet: String = "",
    val label: String = "",
    val playCount: Int = 0,
    val list: String = "",
    val url: String = "",
    val labelUrl: String = "",
    val explicit: Boolean = false,
    val listType: String = "",
    val artistMap: ArtistMap,
    val releaseDate: String = "",
    val subtitle: String = "",
    val name: String = "",
    val albumId: String = "",
    val albumUrl: String = "",
    val hasLyrics: Boolean = false
)

fun Song.getImageUrl(): String? {
    val url = when (image) {
        is List<*> -> {
            (image.lastOrNull() as? Map<*, *>)?.get("link") as? String
        }

        is String -> image
        else -> null
    }

    return url?.replace("http://", "https://")
}


fun Song.toMediaItem(): MediaItem {

    val url=getImageUrl()

    return MediaItem.Builder()
        .setMediaId(id)
        .setUri(downloadUrl?.lastOrNull()?.link ?: "")
        .setMediaMetadata(
        MediaMetadata.Builder()
            .setTitle(name)
            .setArtist(subtitle)
            .setAlbumTitle(album)
            .setArtworkUri(url?.toUri())
            .build()
    ).build()


}


