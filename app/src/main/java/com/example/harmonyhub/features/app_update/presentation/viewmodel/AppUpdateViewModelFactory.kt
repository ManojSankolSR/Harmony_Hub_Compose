package com.example.harmonyhub.features.app_update.presentation.viewmodel

import com.example.harmonyhub.features.album.presentation.viewmodel.AlbumViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.harmonyhub.features.album.data.repository.AlbumRepository
import com.example.harmonyhub.features.app_update.data.repository.AppUpdatesRepository

class AppUpdateViewModelFactory(
    private val repository: AppUpdatesRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(AppUpdateViewModel::class.java)) {
            return AppUpdateViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel")
    }
}