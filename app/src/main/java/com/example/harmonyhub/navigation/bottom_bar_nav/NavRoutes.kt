package com.example.harmonyhub.navigation.bottom_bar_nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import com.example.harmonyhub.features.home.data.remote.models.MusicItemType
import kotlinx.serialization.Serializable

@Serializable
sealed class BottomNavRoutes(val label: String,val icon: String) {


    @Serializable
    data object Home: BottomNavRoutes(label = "Home", icon = "Home");

    @Serializable
    data object Search: BottomNavRoutes(label = "Search", icon = "Search");

    @Serializable
    data object Library: BottomNavRoutes(label = "Library", icon = "Library")

    @Serializable
    data object Settings: BottomNavRoutes(label = "Settings", icon = "Settings")




    companion object{
        val items=listOf(
            Home, Search, Library, Settings
        )

        val icons=mapOf(
            Home.label to Icons.Default.Home,
            Search.label to Icons.Outlined.Search,
            Library.label to Icons.Outlined.List,
            Settings.label to Icons.Outlined.Settings
        )

    }
}


@Serializable
data class PlaylistDetailScreen(
    val  id: String,

): BottomNavRoutes(label = "Settings", icon = "Settings")