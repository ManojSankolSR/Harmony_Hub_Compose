package com.example.harmonyhub.features.serach.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.harmonyhub.core.presentation.components.ErrorView
import com.example.harmonyhub.core.presentation.components.Loader
import com.example.harmonyhub.core.presentation.components.SongsListItem
import com.example.harmonyhub.features.home.presentation.components.MusicItemCard2
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.serach.data.remote.models.*
import com.example.harmonyhub.features.serach.presentation.state.SearchUiState

@Composable
fun SearchResultsContent(
    state: SearchUiState,
    navController: NavHostController,
    musicPlayerViewModel: MusicPlayerViewModel
) {
    when (state) {
        is SearchUiState.Loading -> Loader()
        is SearchUiState.Error -> ErrorView(message = state.message, onRefresh = { }, paddingValues = PaddingValues(0.dp))
        is SearchUiState.Success -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 100.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Top Songs
                state.data.songs?.data?.let { songs ->
                    if (songs.isNotEmpty()) {
                        item {
                            SectionHeader("Songs")
                        }
                        items(songs) { songItem ->
                            val mappedSong = songItem.toSong()
                            SongsListItem(
                                song = mappedSong,
                                viewModel = musicPlayerViewModel,
                                onClick = {
                                    musicPlayerViewModel.setMediaItems(songs.map { it.toSong() }, songs.indexOf(songItem))
                                    musicPlayerViewModel.play()
                                }
                            )
                        }
                    }
                }

                // Albums
                state.data.albums?.data?.let { albums ->
                    if (albums.isNotEmpty()) {
                        item {
                            SectionHeader("Albums")
                            HorizontalResultList(
                                items = albums,
                                navController = navController,
                                musicPlayerViewModel = musicPlayerViewModel
                            )
                        }
                    }
                }

                // Artists
                state.data.artists?.data?.let { artists ->
                    if (artists.isNotEmpty()) {
                        item {
                            SectionHeader("Artists")
                            HorizontalResultList(
                                items = artists,
                                navController = navController,
                                musicPlayerViewModel = musicPlayerViewModel
                            )
                        }
                    }
                }

                // Playlists
                state.data.playlists?.data?.let { playlists ->
                    if (playlists.isNotEmpty()) {
                        item {
                            SectionHeader("Playlists")
                            HorizontalResultList(
                                items = playlists,
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

@Composable
fun <T> HorizontalResultList(
    items: List<T>,
    navController: NavHostController,
    musicPlayerViewModel: MusicPlayerViewModel
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(items) { item ->
            val dataItem = when (item) {
                is AlbumSearchItem -> item.toMusicDataItem()
                is ArtistSearchItem -> item.toMusicDataItem()
                is PlaylistSearchItem -> item.toMusicDataItem()
                else -> null
            }
            dataItem?.let {
                MusicItemCard2(
                    dataItem = it,
                    navController = navController,
                    musicPlayerViewModel = musicPlayerViewModel
                )
            }
        }
    }
}
