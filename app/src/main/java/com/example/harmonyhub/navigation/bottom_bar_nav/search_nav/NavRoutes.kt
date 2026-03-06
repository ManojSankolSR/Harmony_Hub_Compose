package com.example.harmonyhub.navigation.bottom_bar_nav.search_nav

import kotlinx.serialization.Serializable


@Serializable
sealed class SearchNavRoutes {

    @Serializable
    data object SearchScreen: SearchNavRoutes()
}