package com.example.harmonyhub.features.serach.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.harmonyhub.HarmonyHub
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.serach.presentation.components.SearchBar
import com.example.harmonyhub.features.serach.presentation.components.SearchResultsContent
import com.example.harmonyhub.features.serach.presentation.components.TopSearchesContent
import com.example.harmonyhub.features.serach.presentation.viewmodel.SearchViewModel
import com.example.harmonyhub.features.serach.presentation.viewmodel.SearchViewModelFactory
import com.example.harmonyhub.ui.theme.PermanentMarker
import kotlinx.coroutines.delay

@Composable
fun SearchScreen(
    paddingValues: PaddingValues,
    navController: NavHostController,
    musicPlayerViewModel: MusicPlayerViewModel
) {
    val app = LocalContext.current.applicationContext as HarmonyHub
    val repository = app.appContainer.searchRepository
    val viewModel: SearchViewModel = viewModel(factory = SearchViewModelFactory(repository))

    val searchState by viewModel.searchState.collectAsStateWithLifecycle()
    val topSearchesState by viewModel.topSearchesState.collectAsStateWithLifecycle()

    var query by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        viewModel.getTopSearches()
    }

    LaunchedEffect(query) {
        if (query.length >= 2) {
            delay(500)
            viewModel.search(query)
        }
    }

    Scaffold(
        modifier = Modifier.padding(paddingValues),
        topBar = {
            Column(Modifier.padding(16.dp)) {
                Text(
                    "Search",
                    style = MaterialTheme.typography.headlineLarge.copy(fontFamily = PermanentMarker),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                SearchBar(
                    query = query,
                    onQueryChange = { query = it },
                    onSearch = {
                        focusManager.clearFocus()
                        viewModel.search(query)
                    },
                    onClear = { query = "" }
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            if (query.isEmpty()) {
                TopSearchesContent(topSearchesState, navController, musicPlayerViewModel)
            } else {
                SearchResultsContent(searchState, navController, musicPlayerViewModel)
            }
        }
    }
}
