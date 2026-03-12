package com.example.harmonyhub.features.music_player.data.local.entities

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.DownloadUrlItem
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Rights
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.getImageUrl
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive

@Entity(tableName = "songs")
data class SongEntity(
    @PrimaryKey
    val id: String,
    val vlink: String? = null,
    val year: Int = 0,
    val origin: String = "",
    val language: String = "",
    val copyrightText: String = "",
    val type: String = "",
    val Kbps: Boolean = false,
    val headerDesc: String = "",
    val vcode: String? = null,
    val duration: Int = 0,
    val music: String = "",
    val starred: Boolean = false,
    val trillerAvailable: Boolean = false,
    val rights: Rights?,
    val downloadUrl: List<DownloadUrlItem>?,
    val image: String?,
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
    val artistMap: JsonElement?,
    val releaseDate: String = "",
    val subtitle: String = "",
    val name: String = "",
    val albumId: String = "",
    val albumUrl: String = "",
    val hasLyrics: Boolean = false,
    val isPlaying: Boolean = false,
    val currentPosition: Long = 0L,
    val totalDuration: Long = 0L,
    val imageBitmap: Bitmap? = null
)

fun Song.toEntity(isPlaying: Boolean = false, currentPosition: Long = 0L, totalDuration: Long = 0L): SongEntity {
    return SongEntity(
        id = id,
        vlink = vlink,
        year = year,
        origin = origin,
        language = language,
        copyrightText = copyrightText,
        type = type,
        Kbps = Kbps,
        headerDesc = headerDesc,
        vcode = vcode,
        duration = duration,
        music = music,
        starred = starred,
        trillerAvailable = trillerAvailable,
        rights = rights,
        downloadUrl = downloadUrl,
        image = getImageUrl(),
        isDolbyContent = isDolbyContent,
        listCount = listCount,
        album = album,
        lyricsSnippet = lyricsSnippet,
        label = label,
        playCount = playCount,
        list = list,
        url = url,
        labelUrl = labelUrl,
        explicit = explicit,
        listType = listType,
        artistMap = artistMap,
        releaseDate = releaseDate,
        subtitle = subtitle,
        name = name,
        albumId = albumId,
        albumUrl = albumUrl,
        hasLyrics = hasLyrics,
        isPlaying = isPlaying,
        currentPosition = currentPosition,
        totalDuration = totalDuration,
        imageBitmap = imageBitmap
    )
}

fun SongEntity.toSong(): Song {
    return Song(
        id = id,
        vlink = vlink,
        year = year,
        origin = origin,
        language = language,
        copyrightText = copyrightText,
        type = type,
        Kbps = Kbps,
        headerDesc = headerDesc,
        vcode = vcode,
        duration = duration,
        music = music,
        starred = starred,
        trillerAvailable = trillerAvailable,
        rights = rights ?: Rights(),
        downloadUrl = downloadUrl,
        image = if (image != null) JsonPrimitive(image) else null,
        isDolbyContent = isDolbyContent,
        listCount = listCount,
        album = album,
        lyricsSnippet = lyricsSnippet,
        label = label,
        playCount = playCount,
        list = list,
        url = url,
        labelUrl = labelUrl,
        explicit = explicit,
        listType = listType,
        artistMap = artistMap,
        releaseDate = releaseDate,
        subtitle = subtitle,
        name = name,
        albumId = albumId,
        albumUrl = albumUrl,
        hasLyrics = hasLyrics,
        imageBitmap = imageBitmap
    )
}
