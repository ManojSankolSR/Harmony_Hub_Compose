package com.example.harmonyhub.features.storage.presentation.state

import com.example.harmonyhub.features.storage.data.models.TotalStorageInfo

sealed class StorageInfoUiState {
    data class Error(val message: String) : StorageInfoUiState()
    data class Success(
        val info: TotalStorageInfo
    ) : StorageInfoUiState()

    data object Loading : StorageInfoUiState()
}