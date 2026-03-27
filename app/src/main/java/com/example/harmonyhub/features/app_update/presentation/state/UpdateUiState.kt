package com.example.harmonyhub.features.app_update.presentation.state

import com.example.harmonyhub.features.app_update.data.remote.models.AppUpdateInfo
import java.io.File

sealed class UpdateState {
    object Idle : UpdateState()
    object CheckingForUpdates : UpdateState()
    data class Downloading(val progress: Long, val data: AppUpdateInfo) : UpdateState()
    data class Downloaded(val file: File, val data: AppUpdateInfo) : UpdateState()
    object UpToDate : UpdateState()
    data class UpdateAvailable(val data: AppUpdateInfo) : UpdateState()
    data class Error(val message: String) : UpdateState()
}
