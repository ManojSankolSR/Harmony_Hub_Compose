package com.example.harmonyhub.core.presentation.components

import StackedSnackbarHost
import StackedSnakbarHostState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.astro.stockopname.view.fragment.sync.AllIcons
import com.astro.stockopname.view.fragment.sync.SnackbarIcon
import com.example.harmonyhub.core.models.SnackBar
import com.example.harmonyhub.core.services.SnackBarManager


@Composable
fun BoxScope.SnackBar() {

    val scope = rememberCoroutineScope()

    val snackBarHostState = remember {
        StackedSnakbarHostState(scope, StackedSnackbarAnimation.Bounce)
    }

    LaunchedEffect(Unit) {
        SnackBarManager.data.collect {

            when (it) {
                is SnackBar.InfoSnackBar -> {
                    snackBarHostState.showInfoSnackbar(
                        it.title, it.description, it.actionTitle, it.action, it.duration
                    )

                }

                is SnackBar.ErrorSnackBar -> {
                    snackBarHostState.showErrorSnackbar(
                        it.title, it.description, it.actionTitle, it.action, it.duration
                    )

                }

                is SnackBar.SuccessSnackBar -> {
                    snackBarHostState.showSuccessSnackbar(
                        it.title, it.description, it.actionTitle, it.action, it.duration
                    )

                }

                is SnackBar.WarningSnackBar -> {
                    snackBarHostState.showWarningSnackbar(
                        it.title, it.description, it.actionTitle, it.action, it.duration
                    )

                }

                is SnackBar.CustomSnackBar -> {
                    snackBarHostState.showCustomSnackbar(
                        it.content,
                        it.duration,
                    )

                }
            }

        }
    }


    StackedSnackbarHost(
        snackBarHostState, modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 40.dp)
    )
}



