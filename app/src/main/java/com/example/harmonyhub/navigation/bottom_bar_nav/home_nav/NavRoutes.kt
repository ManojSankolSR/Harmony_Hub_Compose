package com.example.harmonyhub.navigation.bottom_bar_nav.home_nav

import kotlinx.serialization.Serializable


@Serializable
sealed class HomeNavRoutes {
    @Serializable
    data object HomeScreen: HomeNavRoutes()
}