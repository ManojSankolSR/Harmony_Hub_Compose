package com.example.harmonyhub.features.song_download.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.harmonyhub.features.song_download.data.repository.DownloadRepository

class DownloadsViewModelFactory(private val downloadRepository: DownloadRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DownloadsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DownloadsViewModel(downloadRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}