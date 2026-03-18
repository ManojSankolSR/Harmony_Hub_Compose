package com.example.harmonyhub.features.home.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import com.example.harmonyhub.core.presentation.components.ErrorView
import com.example.harmonyhub.core.presentation.components.Loader
import com.example.harmonyhub.features.home.presentation.components.HomeList
import com.example.harmonyhub.features.home.presentation.components.TopBar
import com.example.harmonyhub.features.home.presentation.state.HomeUiState
import com.example.harmonyhub.features.home.presentation.viewmodel.HomeViewModel
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    parentPaddingValues: PaddingValues,
    navController: NavHostController,
    musicPlayerViewModel: MusicPlayerViewModel,
    homeViewModel: HomeViewModel
) {

    val state = homeViewModel.state.collectAsState().value;

    fun onRefresh(){
        homeViewModel.observeLanguageAndFetchHomeData()
    }

    Scaffold(
        topBar = { TopBar() },
        contentWindowInsets = WindowInsets(bottom = 0),
    ) { padding ->
        when (state) {
            is HomeUiState.Loading -> {
                Loader(
                    padding = PaddingValues(
                        bottom = parentPaddingValues.calculateBottomPadding(),
                        top=padding.calculateTopPadding()
                    )
                )
            }

            is HomeUiState.Success -> {
                val data = state.data
                HomeList(
                    data,
                    parentPaddingValues,
                    padding,
                    homeViewModel,
                    navController,
                    musicPlayerViewModel
                )
            }

            is HomeUiState.Error -> {
                val message = state.message
                ErrorView(::onRefresh, message,  PaddingValues(
                    bottom = parentPaddingValues.calculateBottomPadding(),
                    top=padding.calculateTopPadding()
                ))

            }

        }

    }


}
