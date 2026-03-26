package com.example.harmonyhub.features.app_update.presentation.state

import com.example.harmonyhub.features.app_update.data.remote.models.AppUpdateInfo

sealed class UpdateState {
    object Idle : UpdateState()
    object CheckingForUpdates : UpdateState()

    data class Updating(val progress: Long) : UpdateState()
    object UpToDate : UpdateState()
    data class UpdateAvailable(val data: AppUpdateInfo) : UpdateState()
    data class Error(val message: String) : UpdateState()
}