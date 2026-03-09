package com.example.harmonyhub.features.artist.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.harmonyhub.core.presentation.components.SongsListItem
import com.example.harmonyhub.features.artist.data.remote.models.ArtistData
import com.example.harmonyhub.features.artist.data.remote.models.toMusicDataItem
import com.example.harmonyhub.features.home.presentation.components.MusicItemCard2
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.navigation.bottom_bar_nav.ArtistDetailsScreen

@Composable
fun ArtistContent(
    artistData: ArtistData,
    navController: NavHostController,
    musicPlayerViewModel: MusicPlayerViewModel,
    paddingValues: PaddingValues
) {
    LazyColumn(
        modifier = Modifier.Companion.fillMaxSize(),
        contentPadding = PaddingValues(bottom = paddingValues.calculateBottomPadding()),

    ) {
        item {
            ArtistHeader(artistData)
        }

        artistData.topSongs?.let { songs ->
            item {
                SectionTitle("Top Songs")
            }
            item { Spacer(Modifier.height(6.dp)) }
            itemsIndexed(songs.take(5)) { index, song ->
                Box(Modifier.padding(horizontal = 4.dp)){
                    SongsListItem(
                        song = song,
                        viewModel = musicPlayerViewModel,
                        onClick = {
                            musicPlayerViewModel.setMediaItems(songs, index)
                            musicPlayerViewModel.play()
                        }
                    )
                }
            }
        }

        item { Spacer(Modifier.height(10.dp)) }

        artistData.topAlbums?.let { albums ->
            if (albums.isNotEmpty()) {
                item {
                    SectionTitle("Top Albums")
                    HorizontalAlbumList(albums, navController, musicPlayerViewModel)
                }
            }
        }

        artistData.singles?.let { singles ->
            if (singles.isNotEmpty()) {
                item {
                    SectionTitle("Singles")
                    HorizontalAlbumList(singles, navController, musicPlayerViewModel)
                }
            }
        }

        artistData.similarArtists?.let { artists ->
            if (artists.isNotEmpty()) {
                item {
                    SectionTitle("Similar Artists")
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 6.dp),
                    ) {
                        items(artists) { artist ->
                            MusicItemCard2(
                                dataItem = artist.toMusicDataItem(),
                                navController = navController,
                                musicPlayerViewModel = musicPlayerViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}