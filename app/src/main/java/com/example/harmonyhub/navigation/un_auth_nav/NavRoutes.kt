package com.example.harmonyhub.navigation.un_auth_nav

import kotlinx.serialization.Serializable


@Serializable
sealed class UnAuthNavRoutes {

    @Serializable
    data object LogInScreen: UnAuthNavRoutes()
}