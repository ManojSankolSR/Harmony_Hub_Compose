package com.example.harmonyhub.features.playlist.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.ArtistsItem
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Modules
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.PlaylistData
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song

@Entity(tableName = "playlist_data")
data class PlaylistEntity(
    @PrimaryKey
    val id: String,
    val firstname: String = "",
    val subtitleDesc: List<String>?,
    val language: String = "",
    val type: String = "",
    val followerCount: Int = 0,
    val headerDesc: String = "",
    val artists: List<ArtistsItem>?,
    val share: Int = 0,
    val image: String = "",
    val isDolbyContent: Boolean = false,
    val lastUpdated: String = "",
    val listCount: Int = 0,
    val fanCount: Int = 0,
    val url: String = "",
    val modules: Modules?,
    val lastname: String = "",
    val explicit: Boolean = false,
    val listType: String = "",
    val userId: String = "",
    val songs: List<Song>?,
    val subtitle: String = "",
    val name: String = "",
    val videoCount: Int = 0,
    val username: String = ""
)

fun PlaylistData.toEntity(): PlaylistEntity {
    return PlaylistEntity(
        id = id,
        firstname = firstname,
        subtitleDesc = subtitleDesc,
        language = language,
        type = type,
        followerCount = followerCount,
        headerDesc = headerDesc,
        artists = artists,
        share = share,
        image = image,
        isDolbyContent = isDolbyContent,
        lastUpdated = lastUpdated,
        listCount = listCount,
        fanCount = fanCount,
        url = url,
        modules = modules,
        lastname = lastname,
        explicit = explicit,
        listType = listType,
        userId = userId,
        songs = songs,
        subtitle = subtitle,
        name = name,
        videoCount = videoCount,
        username = username
    )
}

fun PlaylistEntity.toPlaylistData(): PlaylistData {
    return PlaylistData(
        id = id,
        firstname = firstname,
        subtitleDesc = subtitleDesc,
        language = language,
        type = type,
        followerCount = followerCount,
        headerDesc = headerDesc,
        artists = artists,
        share = share,
        image = image,
        isDolbyContent = isDolbyContent,
        lastUpdated = lastUpdated,
        listCount = listCount,
        fanCount = fanCount,
        url = url,
        modules = modules!!, // Assuming modules is mandatory in PlaylistData as per its definition
        lastname = lastname,
        explicit = explicit,
        listType = listType,
        userId = userId,
        songs = songs,
        subtitle = subtitle,
        name = name,
        videoCount = videoCount,
        username = username
    )
}
