package com.example.harmonyhub.features.serach.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.harmonyhub.core.navigation.MusicItemNavigator
import com.example.harmonyhub.core.presentation.components.ErrorView
import com.example.harmonyhub.core.presentation.components.Loader
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.serach.presentation.state.TopSearchUiState

@Composable
fun TopSearchesContent(
    state: TopSearchUiState,
    navController: NavHostController,
    musicPlayerViewModel: MusicPlayerViewModel
) {
    when (state) {
        is TopSearchUiState.Loading -> Loader()
        is TopSearchUiState.Error -> ErrorView(
            message = state.message,
            onRefresh = { },
            paddingValues = PaddingValues(0.dp)
        )

        is TopSearchUiState.Success -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                item(span = { GridItemSpan(2) }) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Whatshot,
                            contentDescription = "Trending",
                            tint = Color(0xFFE91E63)
                        )
                        Text(
                            text = "Trending Now",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }

                items(state.data) { musicDataItem ->
                    TopSearchItem(
                        dataItem = musicDataItem,
                        onClick = {
                            musicDataItem.type?.let { type ->
                                MusicItemNavigator.navigate(
                                    type,
                                    navController,
                                    musicDataItem,
                                    musicPlayerViewModel
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}

