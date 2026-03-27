package com.example.harmonyhub.features.app_update.presentation.viewmodel

import StackedSnackbarDuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harmonyhub.BuildConfig
import com.example.harmonyhub.core.models.SnackBar
import com.example.harmonyhub.core.services.SnackBarManager
import com.example.harmonyhub.features.app_update.data.remote.models.AppUpdateInfo
import com.example.harmonyhub.features.app_update.data.repository.AppUpdatesRepository
import com.example.harmonyhub.features.app_update.presentation.state.UpdateState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File

class AppUpdateViewModel(
    private val repository: AppUpdatesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UpdateState>(UpdateState.Idle)
    val state = _uiState.asStateFlow()


    init {
        checkForUpdatesInitial()
    }

    fun checkForUpdatesInitial() {
        viewModelScope.launch {
            try {
                val appUpdateInfo = repository.checkForUpdates()
                if (appUpdateInfo.versionCode > BuildConfig.VERSION_CODE) {
                    _uiState.update {
                        UpdateState.UpdateAvailable(appUpdateInfo)
                    }
                }
            } catch (e: Exception) {
                SnackBarManager.show(
                    SnackBar.ErrorSnackBar(
                        "Unable To Check For Updates",
                        e.message ?: "Some error occurred",
                        duration = StackedSnackbarDuration.Short
                    )
                )
            }
        }
    }

    fun checkForUpdates() {
        viewModelScope.launch {
            try {
                _uiState.update {
                    UpdateState.CheckingForUpdates
                }
                val appUpdateInfo = repository.checkForUpdates()
                if (appUpdateInfo.versionCode > BuildConfig.VERSION_CODE) {
                    _uiState.update {
                        UpdateState.UpdateAvailable(appUpdateInfo)
                    }
                } else {
                    _uiState.update {
                        UpdateState.UpToDate
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    UpdateState.Error(e.message ?: "Unknown Error")
                }
            }
        }
    }

    fun downloadUpdate(appUpdateInfo: AppUpdateInfo) {
        viewModelScope.launch {
            try {
                val file = repository.downloadUpdate(appUpdateInfo) { progress ->
                    _uiState.update { UpdateState.Downloading(progress.toLong(), appUpdateInfo) }
                }
                _uiState.update {
                    UpdateState.Downloaded(file, appUpdateInfo)
                }
            } catch (e: Exception) {
                _uiState.update {
                    UpdateState.Error(e.message ?: "Unknown Error")
                }
            }
        }
    }


    fun installUpdate(file: File) {
        try {
            repository.installUpdate(file)
        } catch (e: Exception) {
            _uiState.update {
                UpdateState.Error(e.message ?: "Installation failed")
            }
        }
    }


    fun resetState() {
        _uiState.update { UpdateState.Idle }
    }
}
