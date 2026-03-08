package com.example.harmonyhub.features.album.data.remote.models

data class AlbumModules(
    val recommend: ModuleItem? = null,
    val currentlyTrending: ModuleItem? = null,
    val topAlbumsFromSameYear: ModuleItem? = null,
    val artists: ModuleItem? = null
)