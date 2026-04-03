package com.example.harmonyhub.features.home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.harmonyhub.features.home.data.remote.models.HomeData
import com.example.harmonyhub.features.home.presentation.viewmodel.HomeViewModel
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeList(
    data: HomeData,
    parentPadding: PaddingValues,
    padding: PaddingValues,
    homeViewModel: HomeViewModel,
    navController: NavHostController,
    musicPlayerViewModel: MusicPlayerViewModel
) {

    val list = data.asList()

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(
            bottom = parentPadding.calculateBottomPadding(),
            top = padding.calculateTopPadding()
        )
    ) {
        itemsIndexed(
            items = list,
        ) { colIndex, item ->
            val title = item.title ?: ""
            if (title.isNotEmpty() && !item.data.isNullOrEmpty()) {
                Text(
                    title,
                    Modifier.padding(horizontal = 12.dp),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.W500,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
            }
            Spacer(Modifier.height(6.dp))
            item.data?.let { dataList ->
                if (dataList.isNotEmpty()) {
                    BoxWithConstraints {
                        val parentWidth = maxWidth

                        if (colIndex == 0) {
                            val pagerState = rememberPagerState(
                                initialPage = (Int.MAX_VALUE / 2) - (Int.MAX_VALUE / 2 % dataList.size),
                                pageCount = { Int.MAX_VALUE }
                            )

                            HorizontalPager(
                                state = pagerState,
                                contentPadding = PaddingValues(horizontal = 24.dp),
                                pageSpacing = 2.dp,
                                modifier = Modifier.fillMaxWidth()
                            ) { page ->
                                val musicItem = dataList[page % dataList.size]
                                musicItem?.let {
                                    MusicItemCard1(
                                        data = it,
                                        navController = navController,
                                        musicPlayerViewModel = musicPlayerViewModel,
                                        parentWidth = parentWidth
                                    )
                                }
                            }
                        } else {
                            LazyRow(
                                contentPadding = PaddingValues(horizontal = 8.dp),
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                itemsIndexed(items = dataList) { _, musicItem ->
                                    musicItem?.let {
                                        MusicItemCard2(it, navController, musicPlayerViewModel)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
