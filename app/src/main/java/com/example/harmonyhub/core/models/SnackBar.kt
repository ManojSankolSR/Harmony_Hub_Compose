package com.example.harmonyhub.core.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

enum class SnackBarDuration {
    SHORT,
    LONG,
    INDEFINITE
}

sealed class SnackBar(val duration: SnackBarDuration) {

    data class InfoSnackBar(
        val title: String,
        val description: String? = null,
        val actionTitle: String? = null,
        val action: (() -> Unit)? = null,
        val snackBarDuration: SnackBarDuration = SnackBarDuration.SHORT
    ): SnackBar(snackBarDuration)

    data class ErrorSnackBar(
        val title: String,
        val description: String? = null,
        val actionTitle: String? = null,
        val action: (() -> Unit)? = null,
        val snackBarDuration: SnackBarDuration = SnackBarDuration.SHORT
    ): SnackBar(snackBarDuration)

    data class SuccessSnackBar(
        val title: String,
        val description: String? = null,
        val actionTitle: String? = null,
        val action: (() -> Unit)? = null,
        val snackBarDuration: SnackBarDuration = SnackBarDuration.SHORT
    ): SnackBar(snackBarDuration)

    data class WarningSnackBar(
        val title: String,
        val description: String? = null,
        val actionTitle: String? = null,
        val action: (() -> Unit)? = null,
        val snackBarDuration: SnackBarDuration = SnackBarDuration.SHORT
    ): SnackBar(snackBarDuration)

    data class CustomSnackBar(
        val content: @Composable ((() -> Unit) -> Unit),
        val snackBarDuration: SnackBarDuration = SnackBarDuration.SHORT
    ): SnackBar(snackBarDuration)
}

val SnackBar.icon: ImageVector
    get() = when (this) {
        is SnackBar.InfoSnackBar -> Icons.Default.Info
        is SnackBar.ErrorSnackBar -> Icons.Default.Error
        is SnackBar.SuccessSnackBar -> Icons.Default.CheckCircle
        is SnackBar.WarningSnackBar -> Icons.Default.Warning
        is SnackBar.CustomSnackBar -> Icons.Default.Info
    }

val SnackBar.typeColor: Color
    @Composable
    get() = when (this) {
        is SnackBar.InfoSnackBar -> MaterialTheme.colorScheme.primary
        is SnackBar.ErrorSnackBar -> MaterialTheme.colorScheme.error
        is SnackBar.SuccessSnackBar -> Color(0xFF4CAF50)
        is SnackBar.WarningSnackBar -> Color(0xFFFFA000)
        is SnackBar.CustomSnackBar -> MaterialTheme.colorScheme.primary
    }
