package com.example.harmonyhub.features.storage.presentation.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.harmonyhub.features.storage.data.models.TotalStorageInfo
import com.example.harmonyhub.features.storage.data.repository.StorageRepository
import com.example.harmonyhub.features.storage.presentation.state.StorageInfoUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@RequiresApi(Build.VERSION_CODES.O)
class StorageViewModel(private val repository: StorageRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<StorageInfoUiState>(StorageInfoUiState.Loading)

    val state = _uiState.asStateFlow()

    init {
        getTotalStorageStats()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getTotalStorageStats() {

        _uiState.update {
            StorageInfoUiState.Loading
        }

        val deviceStorageInfo = repository.getDeviceStorageInfo()
        val appUsedStorage = repository.getAppUsedStorageSize()

        val storageInfo = TotalStorageInfo(
            deviceTotalStorage = deviceStorageInfo.totalSpace,
            deviceAvailableStorage = deviceStorageInfo.availableSpace,
            deviceUsedStorage = deviceStorageInfo.usedSpace-appUsedStorage,
            appUsedStorage
        )

        _uiState.update {
            StorageInfoUiState.Success(storageInfo)
        }
    }

    fun clearAppData() {
        repository.clearAppData()
    }

}
