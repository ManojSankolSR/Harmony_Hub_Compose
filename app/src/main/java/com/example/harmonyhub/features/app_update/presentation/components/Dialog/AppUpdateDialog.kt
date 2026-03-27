package com.example.harmonyhub.features.app_update.presentation.components.Dialog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.harmonyhub.BuildConfig
import com.example.harmonyhub.features.app_update.presentation.state.UpdateState
import com.example.harmonyhub.features.app_update.presentation.viewmodel.AppUpdateViewModel

@Composable
fun AppUpdateDialog(viewModel: AppUpdateViewModel) {
    val state = viewModel.state.collectAsState().value

    if (state == UpdateState.Idle) return

    val currentUpdateInfo = when (state) {
        is UpdateState.UpdateAvailable -> state.data
        is UpdateState.Downloading -> state.data
        is UpdateState.Downloaded -> state.data
        else -> null
    }

    val isMandatory = currentUpdateInfo?.mandatory == true

    val isNonDismissible = when (state) {
        UpdateState.CheckingForUpdates -> true
        is UpdateState.Downloading -> true
        is UpdateState.UpdateAvailable -> isMandatory
        is UpdateState.Downloaded -> isMandatory
        else -> false // Idle, UpToDate, Error are dismissible
    }

    Dialog(
        onDismissRequest = { if (!isNonDismissible) viewModel.resetState() },
        properties = DialogProperties(
            dismissOnBackPress = !isNonDismissible,
            dismissOnClickOutside = !isNonDismissible,
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(if (state is UpdateState.UpdateAvailable) 0.9f else 0.85f)
                .wrapContentHeight(),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
        ) {
            when (state) {
                is UpdateState.CheckingForUpdates -> CheckingForUpdatesContent()
                is UpdateState.UpdateAvailable -> UpdateAvailableContent(
                    updateInfo = state.data,
                    onDismiss = { viewModel.resetState() },
                    onDownload = { viewModel.downloadUpdate(state.data) }
                )
                is UpdateState.Downloading -> UpdatingContent(progress = state.progress)
                is UpdateState.Downloaded -> DownloadedContent(
                    file = state.file,
                    onInstall = { viewModel.installUpdate(it) },
                    onDismiss = { viewModel.resetState() },
                    isMandatory = isMandatory
                )
                UpdateState.UpToDate -> MessageContent(
                    title = "App Up to Date",
                    message = "You are already using the latest version of Harmony Hub ${BuildConfig.VERSION_NAME}",
                    icon = Icons.Rounded.CheckCircle,
                    iconColor = MaterialTheme.colorScheme.primary,
                    onDismiss = { viewModel.resetState() }
                )
                is UpdateState.Error -> MessageContent(
                    title = "Update Error",
                    message = state.message,
                    icon = Icons.Rounded.Error,
                    iconColor = MaterialTheme.colorScheme.error,
                    onDismiss = { viewModel.resetState() }
                )
                is UpdateState.Idle -> {}
            }
        }
    }
}
