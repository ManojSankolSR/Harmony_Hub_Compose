package com.example.harmonyhub.features.serach.data.remote.models

import com.example.harmonyhub.features.home.data.remote.models.MusicDataItem
import com.example.harmonyhub.features.home.data.remote.models.MusicItemType
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Rights
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song
import com.google.gson.Gson

fun SongSearchItem.toSong() = Song(
    id = id,
    name = name,
    subtitle = subtitle,
    album = album ?: "",
    image = Gson().toJsonTree(image),
    url = url,
    type = "song",
    rights = Rights(),
    downloadUrl = null
)

fun AlbumSearchItem.toMusicDataItem() = MusicDataItem(
    id = id,
    name = name,
    subtitle = subtitle,
    type = MusicItemType.ALBUM,
    image = Gson().toJsonTree(image),
    url = url
)

fun ArtistSearchItem.toMusicDataItem() = MusicDataItem(
    id = id,
    name = name,
    subtitle = subtitle,
    type = MusicItemType.ARTIST,
    image = Gson().toJsonTree(image),
    url = url
)

fun PlaylistSearchItem.toMusicDataItem() = MusicDataItem(
    id = id,
    name = name,
    subtitle = subtitle,
    type = MusicItemType.PLAYLIST,
    image = Gson().toJsonTree(image),
    url = url
)
