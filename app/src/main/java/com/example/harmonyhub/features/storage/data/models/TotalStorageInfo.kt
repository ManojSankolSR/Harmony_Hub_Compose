package com.example.harmonyhub.features.storage.data.models

data class TotalStorageInfo (
    val deviceTotalStorage: Long,
    val deviceAvailableStorage: Long,
    val deviceUsedStorage: Long,
    val appUsedStorage: Long
)