package com.example.harmonyhub.navigation.root_nav

import kotlinx.serialization.Serializable


@Serializable
sealed class RootNavRoutes {

    @Serializable
    data object UnAuthenticatedRoutes: RootNavRoutes()

    @Serializable
    data object AuthenticatedRoutes: RootNavRoutes()


}