package com.example.harmonyhub.features.home.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.harmonyhub.HarmonyHub
import com.example.harmonyhub.R
import com.example.harmonyhub.core.presentation.components.ErrorView
import com.example.harmonyhub.core.presentation.components.Loader
import com.example.harmonyhub.features.home.presentation.components.HomeList
import com.example.harmonyhub.features.home.presentation.state.HomeUiState
import com.example.harmonyhub.features.home.presentation.viewmodel.HomeViewModel
import com.example.harmonyhub.features.home.presentation.viewmodel.HomeViewModelFactory
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.ui.theme.PermanentMarker

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(parentPaddingValues: PaddingValues, navController: NavHostController,musicPlayerViewModel: MusicPlayerViewModel) {
    val app = LocalContext.current.applicationContext as HarmonyHub;
    val homeViewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory(app.appContainer.homeRepository)
    )
    val state = homeViewModel.state.collectAsState().value;


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(
                            12.dp,
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(R.drawable.app_icon),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(
                                MaterialTheme.colorScheme.onSurface
                            ),

                            )
                        Text(
                            "Harmony Hub ",
                            style = MaterialTheme.typography.headlineLarge.copy(fontFamily = PermanentMarker)
                        )
                    }
                }
            )
        },
        contentWindowInsets = WindowInsets(bottom = 0),
//        modifier = Modifier.padding(bottom = parentPaddingValues.calculateBottomPadding())

    ) { padding ->

        when (state) {
            is HomeUiState.Loading -> {
                Loader(padding)
            }

            is HomeUiState.Success -> {
                val data = state.data.data
                HomeList(data, parentPaddingValues, padding,homeViewModel,navController,musicPlayerViewModel)
            }

            is HomeUiState.Error -> {
                val message = state.message
                ErrorView(homeViewModel::fetchHomeData,message,padding)

            }

        }

    }


}
