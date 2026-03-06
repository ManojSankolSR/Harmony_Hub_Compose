package com.example.harmonyhub.navigation.auth_nav

import kotlinx.serialization.Serializable

@Serializable
sealed class AuthNavRoutes {

    @Serializable
    data object  BottomNav: AuthNavRoutes()

    @Serializable
    data object MusicPlayer: AuthNavRoutes()
}