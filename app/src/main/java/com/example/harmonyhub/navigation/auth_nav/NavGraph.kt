package com.example.harmonyhub.navigation.auth_nav

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.harmonyhub.features.auth.presentation.viewmodel.AuthViewModel
import com.example.harmonyhub.features.music_player.presentation.screens.MusicPlayer
import com.example.harmonyhub.navigation.bottom_bar_nav.BottomBarNavGraph
import com.example.harmonyhub.navigation.root_nav.RootNavRoutes

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.authNavGraph(navController: NavHostController, authViewModel: AuthViewModel) {
    navigation<RootNavRoutes.AuthenticatedRoutes>(
        startDestination = AuthNavRoutes.BottomNav
    ) {
        composable<AuthNavRoutes.BottomNav> {
            BottomBarNavGraph(navController,authViewModel)
        }
        composable<AuthNavRoutes.MusicPlayer>(
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Up,
                    tween(300)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Down,
                    tween(300)
                )
            }
        ) {
            MusicPlayer(navController)
        }
    }
}