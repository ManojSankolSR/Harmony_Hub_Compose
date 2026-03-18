package com.example.harmonyhub.features.auth.presentation.state

import com.example.harmonyhub.features.auth.data.remote.models.User

data class AuthUiState(val user: User?, val isLoading: Boolean = true, val error: String?)