package com.example.harmonyhub.features.artist.data.remote.models

import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song
import com.example.harmonyhub.features.album.data.remote.models.AlbumData
import com.google.gson.JsonElement
import com.google.gson.JsonArray
import com.google.gson.JsonObject

data class ArtistData(
    val id: String = "",
    val name: String = "",
    val subtitle: String = "",
    val image: JsonElement? = null,
    val followerCount: Int = 0,
    val type: String = "",
    val isVerified: Boolean = false,
    val dominantLanguage: String = "",
    val dominantType: String = "",
    val topSongs: List<Song>? = null,
    val topAlbums: List<AlbumData>? = null,
    val dedicatedArtistPlaylist: List<ArtistPlaylist>? = null,
    val featuredArtistPlaylist: List<ArtistPlaylist>? = null,
    val singles: List<AlbumData>? = null,
    val latestRelease: List<AlbumData>? = null,
    val similarArtists: List<SimilarArtist>? = null,
    val isRadioPresent: Boolean = false,
    val bio: List<JsonElement>? = null,
    val dob: String = "",
    val fb: String = "",
    val twitter: String = "",
    val wiki: String = "",
    val urls: Map<String, String>? = null,
    val availableLanguages: List<String>? = null,
    val fanCount: Int = 0,
    val isFollowed: Boolean = false,
    val modules: Map<String, ArtistModuleInfo>? = null
)

fun ArtistData.getImageUrl(): String? {
    return when (val img = image) {
        is JsonArray -> {
            val lastItem = img.lastOrNull() as? JsonObject
            lastItem?.get("link")?.asString
        }
        is JsonObject -> {
            img.get("link")?.asString
        }
        else -> if (img != null && img.isJsonPrimitive) img.asString else null
    }?.replace("http://", "https://")
}
