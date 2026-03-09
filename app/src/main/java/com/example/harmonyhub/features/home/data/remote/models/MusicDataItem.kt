package com.example.harmonyhub.features.home.data.remote.models

import com.example.harmonyhub.features.playlist.data.remote.models.playlist.DownloadUrlItem
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Rights
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject

data class MusicDataItem(
    val id: String = "",
    val name: String? = "",
    val subtitle: String? = "",
    val type: MusicItemType? = null,
    val url: String? = "",
    val image: JsonElement? = null, // Handles both String and List using Gson's JsonElement
    val explicit: Boolean? = false,
    val duration: Int? = 0,
    val play_count: Long? = 0,
    val year: Int? = 0,
    val language: String? = "",
    val song_count: Int? = 0,
    val songs: List<MusicDataItem>? = emptyList(),
    val album: String? = "",
    val album_id: String? = "",
    val album_url: String? = "",
    val download_url: List<DownloadUrlItem>? = emptyList(),
    val artist_map:JsonElement? = null
)

fun MusicDataItem.getImageUrl(): String? {
    return when (val img = image) {
        is JsonArray -> {
            val lastItem = img.lastOrNull() as? JsonObject
            lastItem?.get("link")?.asString
        }
        is JsonObject -> {
             img.get("link")?.asString
        }
        else -> img?.asString
    }?.replace("http://", "https://")
}

fun JsonElement?.toArtistMap(): ArtistMap? {
    if (this == null || !this.isJsonObject) return null
    return Gson().fromJson(this, ArtistMap::class.java)
}

fun MusicDataItem.toSong(): Song {
    return Song(
        id = id,
        name = name ?: "",
        subtitle = subtitle ?: "",
        type = type?.name?.lowercase() ?: "song",
        url = url ?: "",
        image = image,
        explicit = explicit ?: false,
        duration = duration ?: 0,
        playCount = play_count?.toInt() ?: 0,
        year = year ?: 0,
        language = language ?: "",
        album = album ?: "",
        albumId = album_id ?: "",
        albumUrl = album_url ?: "",
        downloadUrl = download_url,
        artistMap = artist_map,
        rights = Rights()
    )
}
