package com.example.harmonyhub.navigation.un_auth_nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.harmonyhub.features.auth.presentation.viewmodel.AuthViewModel
import com.example.harmonyhub.features.auth.presentation.screens.LoginScreen
import com.example.harmonyhub.navigation.root_nav.RootNavRoutes

fun NavGraphBuilder.unAuthNavGraph(
    navController: NavHostController,
    viewModel: AuthViewModel
) {
    navigation<RootNavRoutes.UnAuthenticatedRoutes>(
        startDestination = UnAuthNavRoutes.LogInScreen
    ) {
        composable<UnAuthNavRoutes.LogInScreen> {
            LoginScreen(viewModel)
        }

    }
}