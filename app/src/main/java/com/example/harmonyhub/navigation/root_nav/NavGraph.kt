package com.example.harmonyhub.navigation.root_nav

import StackedSnackbarHost
import StackedSnakbarHostState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.harmonyhub.HarmonyHub
import com.example.harmonyhub.core.presentation.components.Loader
import com.example.harmonyhub.core.presentation.components.SnackBar
import com.example.harmonyhub.core.services.SnackBarManager
import com.example.harmonyhub.features.auth.presentation.viewmodel.AuthViewModel
import com.example.harmonyhub.features.auth.presentation.viewmodel.AuthViewModelFactory
import com.example.harmonyhub.navigation.auth_nav.authNavGraph
import com.example.harmonyhub.navigation.un_auth_nav.unAuthNavGraph
import kotlinx.coroutines.launch


@Composable
fun RootNavGraph() {

    val navController = rememberNavController();

    val app = LocalContext.current.applicationContext as HarmonyHub;

    val userRepository = app.appContainer.userRepository;

    val authViewModel: AuthViewModel = viewModel(
        factory = AuthViewModelFactory(userRepository)
    )

    val state by authViewModel.state.collectAsState();

    if (state.isLoading) {
        Loader()
        return
    }

    Box(modifier = Modifier.fillMaxSize()) {

        NavHost(
            navController = navController,
            startDestination =
                if (state.user == null)
                    RootNavRoutes.UnAuthenticatedRoutes
                else
                    RootNavRoutes.AuthenticatedRoutes
        ) {
            unAuthNavGraph(navController, authViewModel)
            authNavGraph(navController, authViewModel)
        }

        SnackBar()


    }
}