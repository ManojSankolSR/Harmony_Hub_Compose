package com.example.harmonyhub.features.album.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.harmonyhub.features.album.data.remote.models.AlbumData
import com.example.harmonyhub.features.album.data.remote.models.AlbumModules
import com.example.harmonyhub.features.home.data.remote.models.ArtistMap
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song
import com.google.gson.JsonElement

@Entity(tableName = "album_data")
data class AlbumEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val subtitle: String,
    val type: String,
    val language: String,
    val playCount: Long,
    val duration: Int,
    val explicit: Boolean,
    val year: Int,
    val url: String,
    val headerDesc: String,
    val listCount: Int,
    val listType: String,
    val image: JsonElement?,
    val artistMap: ArtistMap?,
    val songCount: Int,
    val isDolbyContent: Boolean,
    val copyrightText: String,
    val labelUrl: String,
    val songs: List<Song>?,
    val modules: AlbumModules?
)

fun AlbumEntity.toAlbumData(): AlbumData {
    return AlbumData(
        id = id,
        name = name,
        subtitle = subtitle,
        type = type,
        language = language,
        playCount = playCount,
        duration = duration,
        explicit = explicit,
        year = year,
        url = url,
        headerDesc = headerDesc,
        listCount = listCount,
        listType = listType,
        image = image,
        artistMap = artistMap,
        songCount = songCount,
        isDolbyContent = isDolbyContent,
        copyrightText = copyrightText,
        labelUrl = labelUrl,
        songs = songs,
        modules = modules
    )
}

fun AlbumData.toAlbumEntity(): AlbumEntity {
    return AlbumEntity(
        id = id,
        name = name,
        subtitle = subtitle,
        type = type,
        language = language,
        playCount = playCount,
        duration = duration,
        explicit = explicit,
        year = year,
        url = url,
        headerDesc = headerDesc,
        listCount = listCount,
        listType = listType,
        image = image,
        artistMap = artistMap,
        songCount = songCount,
        isDolbyContent = isDolbyContent,
        copyrightText = copyrightText,
        labelUrl = labelUrl,
        songs = songs,
        modules = modules
    )
}
