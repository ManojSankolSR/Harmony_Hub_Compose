package com.example.harmonyhub.core.presentation.state

import com.example.harmonyhub.core.models.User


data class AuthUiState(val user: User?, val isLoading: Boolean = true, val error: String?)