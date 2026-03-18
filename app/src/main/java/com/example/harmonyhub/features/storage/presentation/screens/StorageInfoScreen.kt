package com.example.harmonyhub.features.storage.presentation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import com.example.harmonyhub.core.presentation.components.ErrorView
import com.example.harmonyhub.core.presentation.components.Loader
import com.example.harmonyhub.features.storage.presentation.components.StorageInfoSuccess
import com.example.harmonyhub.features.storage.presentation.components.StorageTopAppBar
import com.example.harmonyhub.features.storage.presentation.state.StorageInfoUiState
import com.example.harmonyhub.features.storage.presentation.viewmodel.StorageViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun StorageInfoScreen(
    storageViewModel: StorageViewModel,
    parentPaddingValues: PaddingValues,
    navController: NavHostController
) {

    val state = storageViewModel.state.collectAsState().value;

    fun getStorageData(){
        storageViewModel.getTotalStorageStats()
    }

    LaunchedEffect(Unit) {
        getStorageData()
    }

    Scaffold(
        topBar = {
            StorageTopAppBar(navController)
        }
    ) { padding ->
        when (state) {
            is StorageInfoUiState.Error -> {
                ErrorView(
                    onRefresh = ::getStorageData,
                    message = state.message,
                    paddingValues = PaddingValues(
                        top = padding.calculateTopPadding(),
                        bottom = parentPaddingValues.calculateBottomPadding()
                    )
                )
            }

            StorageInfoUiState.Loading -> {
                Loader(
                    padding = PaddingValues(
                        top = padding.calculateTopPadding(),
                        bottom = parentPaddingValues.calculateBottomPadding()
                    )
                )
            }

            is StorageInfoUiState.Success -> {
                StorageInfoSuccess(state, parentPaddingValues, padding, storageViewModel)
            }
        }
    }
}
