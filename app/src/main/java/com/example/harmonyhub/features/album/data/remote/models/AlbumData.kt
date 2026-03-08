package com.example.harmonyhub.features.album.data.remote.models

import com.example.harmonyhub.features.home.data.remote.models.ArtistMap
import com.example.harmonyhub.features.home.data.remote.models.ImageItem
import com.example.harmonyhub.features.home.data.remote.models.MusicDataItem
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import kotlin.collections.lastOrNull

data class AlbumData(
    val id: String = "",
    val name: String = "",
    val subtitle: String = "",
    val type: String = "",
    val language: String = "",
    val playCount: Long = 0,
    val duration: Int = 0,
    val explicit: Boolean = false,
    val year: Int = 0,
    val url: String = "",
    val headerDesc: String = "",
    val listCount: Int = 0,
    val listType: String = "",
    val image: JsonElement? = null,
    val artistMap: ArtistMap? = null,
    val songCount: Int = 0,
    val isDolbyContent: Boolean = false,
    val copyrightText: String = "",
    val labelUrl: String = "",
    val songs: List<Song>? = null,
    val modules: AlbumModules? = null
)


fun AlbumData.getImageUrl(): String? {
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