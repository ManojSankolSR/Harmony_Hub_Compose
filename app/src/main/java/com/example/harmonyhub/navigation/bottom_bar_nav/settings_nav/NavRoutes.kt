package com.example.harmonyhub.navigation.bottom_bar_nav.settings_nav

import kotlinx.serialization.Serializable

@Serializable
sealed class SettingsNavRoutes {

    @Serializable
    data object SettingsScreen: SettingsNavRoutes()

    @Serializable
    data object StorageInfoScreen: SettingsNavRoutes()
}