package com.example.harmonyhub.navigation.root_nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.harmonyhub.HarmonyHub
import com.example.harmonyhub.core.presentation.components.Loader
import com.example.harmonyhub.core.presentation.viewmodel.AuthViewModel
import com.example.harmonyhub.core.presentation.viewmodel.AuthViewModelFactory
import com.example.harmonyhub.navigation.auth_nav.authNavGraph
import com.example.harmonyhub.navigation.un_auth_nav.unAuthNavGraph


@Composable
fun RootNavGraph() {

    val navController = rememberNavController();

    val app = LocalContext.current.applicationContext as HarmonyHub;

    val userRepository = app.appContainer.userRepository;

    val authViewModel: AuthViewModel = viewModel(
        factory = AuthViewModelFactory(userRepository)
    )

    val state by authViewModel.state.collectAsState();

    if(state.isLoading){
        Loader()
        return
    }


    NavHost(
        navController = navController,
        startDestination =
            if (state.user == null)
                RootNavRoutes.UnAuthenticatedRoutes
            else
                RootNavRoutes.AuthenticatedRoutes
    ) {
        unAuthNavGraph(navController,authViewModel)
        authNavGraph(navController,authViewModel)
    }
}