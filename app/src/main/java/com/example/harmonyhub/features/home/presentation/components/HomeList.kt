package com.example.harmonyhub.features.home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeList(
    data: HomeData,
    parentPadding: PaddingValues,
    padding:PaddingValues,
    homeViewModel: HomeViewModel,
    navController: NavHostController
) {

    val list = data.asList();

    LazyColumn(
//        Modifier.padding(padding),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(bottom = parentPadding.calculateBottomPadding(), top = padding.calculateTopPadding())
    ) {
        itemsIndexed(
            items = list,
        ) { colIndex, item ->
            val title = item.title ?: ""
            if(title.isNotEmpty())
                Text(
                    title,
                    Modifier.padding(horizontal = 12.dp),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.W500,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
            Spacer(Modifier.height(8.dp))
            item.data?.let { dataList ->
                if (dataList.isNotEmpty())
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 8.dp)
                    ) {
                        itemsIndexed(
                            items = dataList
                        ) { index, musicItem ->
                            musicItem?.let {
                                when (colIndex) {
                                    0 -> MusicItemCard1(it, navController)
                                    else -> MusicItemCard2(it, navController)
                                }
                            }
                        }
                    }
            }
        }
    }

}