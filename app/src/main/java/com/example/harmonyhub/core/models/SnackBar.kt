package com.example.harmonyhub.core.models

import StackedSnackbarDuration
import androidx.compose.runtime.Composable

sealed class SnackBar {

    data class InfoSnackBar(
        val title: String,
        val description: String? = null,
        val actionTitle: String? = null,
        val action: (() -> Unit)? = null,
        val duration: StackedSnackbarDuration
    ):SnackBar()

    data class ErrorSnackBar(
        val title: String,
        val description: String? = null,
        val actionTitle: String? = null,
        val action: (() -> Unit)? = null,
        val duration: StackedSnackbarDuration
    ):SnackBar()

    data class SuccessSnackBar(
        val title: String,
        val description: String? = null,
        val actionTitle: String? = null,
        val action: (() -> Unit)? = null,
        val duration: StackedSnackbarDuration
    ):SnackBar()

    data class WarningSnackBar(
        val title: String,
        val description: String? = null,
        val actionTitle: String? = null,
        val action: (() -> Unit)? = null,
        val duration: StackedSnackbarDuration
    ):SnackBar()

    data class CustomSnackBar(
        val content: @Composable ((() -> Unit) -> Unit),
        val duration: StackedSnackbarDuration
    ):SnackBar()
}