package com.example.harmonyhub.core.navigation

import android.util.Log
import androidx.navigation.NavHostController
import com.example.harmonyhub.core.models.SnackBar
import com.example.harmonyhub.core.services.SnackBarManager
import com.example.harmonyhub.features.home.data.remote.models.MusicDataItem
import com.example.harmonyhub.features.home.data.remote.models.MusicItemType
import com.example.harmonyhub.features.home.data.remote.models.toSong
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.navigation.auth_nav.AuthNavRoutes
import com.example.harmonyhub.navigation.bottom_bar_nav.AlbumDetailsScreen
import com.example.harmonyhub.navigation.bottom_bar_nav.ArtistDetailsScreen
import com.example.harmonyhub.navigation.bottom_bar_nav.PlaylistDetailScreen

object MusicItemNavigator {

    suspend fun navigate(
        type: MusicItemType,
        navController: NavHostController,
        data: MusicDataItem,
        musicPlayerViewModel: MusicPlayerViewModel
    ) {
        when (type) {
            MusicItemType.PLAYLIST -> {
                navController.navigate(PlaylistDetailScreen(data.id))
            }

            MusicItemType.ALBUM -> {
                navController.navigate(AlbumDetailsScreen(data.id))
            }

            MusicItemType.SONG -> {
                val song=data.toSong()
                if(song.downloadUrl.isNullOrEmpty()){
                    musicPlayerViewModel.fetchAndPlaySongs(data.id)
                }else{
                    musicPlayerViewModel.setMediaItems(listOf(song))
                    musicPlayerViewModel.play()
                }
            }

            MusicItemType.ARTIST -> {
                navController.navigate(ArtistDetailsScreen(data.id))
            }

            MusicItemType.RADIO_STATION -> {
                musicPlayerViewModel.playRadio(data)
            }

            MusicItemType.SHOW -> {
                SnackBarManager.show(
                    SnackBar.ErrorSnackBar(
                        "Not Implemented",
                        "Implementation is still in progress",
                        null,
                        null,
                        StackedSnackbarDuration.Short
                    )
                )
            }

            MusicItemType.CHANNEL -> {
                SnackBarManager.show(
                    SnackBar.ErrorSnackBar(
                        "Not Implemented",
                        "Implementation is still in progress",
                        null,
                        null,
                        StackedSnackbarDuration.Short
                    )
                )
            }

            MusicItemType.SEASON-> {
                SnackBarManager.show(
                    SnackBar.ErrorSnackBar(
                        "Not Implemented",
                        "Implementation is still in progress",
                        null,
                        null,
                        StackedSnackbarDuration.Short
                    )
                )
            }

            MusicItemType.EPISODE -> {
                SnackBarManager.show(
                    SnackBar.ErrorSnackBar(
                        "Not Implemented",
                        "Implementation is still in progress",
                        null,
                        null,
                        StackedSnackbarDuration.Short
                    )
                )
            }

            else -> {
                Log.d("MusicItemNavigator", "navigate: $type ${data.id}")
            }
        }
    }
}