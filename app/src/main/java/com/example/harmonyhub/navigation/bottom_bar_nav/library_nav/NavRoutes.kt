package com.example.harmonyhub.navigation.bottom_bar_nav.library_nav

import kotlinx.serialization.Serializable

@Serializable
sealed class LibraryNavRoutes {
    @Serializable
    data object LibraryScreen: LibraryNavRoutes()
}