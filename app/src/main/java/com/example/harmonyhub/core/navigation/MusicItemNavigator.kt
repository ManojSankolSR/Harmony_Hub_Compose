package com.example.harmonyhub.core.navigation

import android.util.Log
import androidx.navigation.NavHostController
import com.example.harmonyhub.features.home.data.remote.models.MusicDataItem
import com.example.harmonyhub.features.home.data.remote.models.MusicItemType
import com.example.harmonyhub.features.home.data.remote.models.toSong
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.navigation.auth_nav.AuthNavRoutes
import com.example.harmonyhub.navigation.bottom_bar_nav.AlbumDetailsScreen
import com.example.harmonyhub.navigation.bottom_bar_nav.PlaylistDetailScreen

object MusicItemNavigator {

    fun navigate(type: MusicItemType, navController: NavHostController, data: MusicDataItem,musicPlayerViewModel: MusicPlayerViewModel) {
        when (type) {
            MusicItemType.PLAYLIST -> {
                navController.navigate(PlaylistDetailScreen(data.id))
            }
            MusicItemType.ALBUM -> {
                navController.navigate(AlbumDetailsScreen(data.id))
            }
            MusicItemType.SONG -> {
                musicPlayerViewModel.setMediaItems(listOf(data.toSong()))
                musicPlayerViewModel.play()
            }
            else -> {
                Log.d("MusicItemNavigator", "navigate: $type ${data.id}")
            }
        }
    }
}