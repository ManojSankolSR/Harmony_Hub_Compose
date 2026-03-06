package com.example.harmonyhub.features.playlist.data.remote.models.playlist

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


