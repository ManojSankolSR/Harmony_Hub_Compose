package com.example.harmonyhub.core.navigation

import android.util.Log
import androidx.navigation.NavHostController
import com.example.harmonyhub.features.home.data.remote.models.MusicDataItem
import com.example.harmonyhub.features.home.data.remote.models.MusicItemType
import com.example.harmonyhub.navigation.auth_nav.AuthNavRoutes
import com.example.harmonyhub.navigation.bottom_bar_nav.PlaylistDetailScreen

object MusicItemNavigator {

    fun navigate(type: MusicItemType, navController: NavHostController, data: MusicDataItem) {
        when (type) {
            MusicItemType.PLAYLIST -> {
                Log.d("MusicItemNavigator", "navigate: $type ${data.id}")
                navController.navigate(PlaylistDetailScreen(data.id))
            }

            else -> {
                Log.d("MusicItemNavigator", "navigate: $type ${data.id}")
            }
        }
    }
}