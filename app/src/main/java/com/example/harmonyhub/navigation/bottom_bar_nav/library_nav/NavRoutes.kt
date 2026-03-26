package com.example.harmonyhub.navigation.bottom_bar_nav.library_nav

import kotlinx.serialization.Serializable

@Serializable
sealed class LibraryNavRoutes {
    @Serializable
    data object LibraryScreen : LibraryNavRoutes()

    @Serializable
    data object LocalPlaylists : LibraryNavRoutes()

    @Serializable
    data class LocalPlaylistDetails(val id: Int, val name: String) : LibraryNavRoutes()

    @Serializable
    data object DownloadedSongs : LibraryNavRoutes()

    @Serializable
    data object LikedSongs : LibraryNavRoutes()
}