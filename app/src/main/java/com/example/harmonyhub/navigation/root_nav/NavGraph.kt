package com.example.harmonyhub.navigation.root_nav

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.harmonyhub.HarmonyHub
import com.example.harmonyhub.core.presentation.components.LoaderOverlay
import com.example.harmonyhub.core.presentation.components.LoaderView
import com.example.harmonyhub.core.presentation.components.SnackBar
import com.example.harmonyhub.features.auth.presentation.viewmodel.AuthViewModel
import com.example.harmonyhub.features.auth.presentation.viewmodel.AuthViewModelFactory
import com.example.harmonyhub.navigation.auth_nav.authNavGraph
import com.example.harmonyhub.navigation.un_auth_nav.unAuthNavGraph


@RequiresApi(Build.VERSION_CODES.O)
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
        LoaderView()
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
        LoaderOverlay()
    }
}