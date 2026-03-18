package com.example.harmonyhub.features.storage.presentation.components

import android.os.Build
import android.text.format.Formatter
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SdStorage
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.harmonyhub.features.storage.presentation.state.StorageInfoUiState
import com.example.harmonyhub.features.storage.presentation.viewmodel.StorageViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun StorageInfoSuccess(
    state: StorageInfoUiState.Success,
    parentPaddingValues: PaddingValues,
    paddingValues: PaddingValues,
    viewModel: StorageViewModel
) {
    val context = LocalContext.current
    val info = state.info
    val otherUsedSize = (info.deviceUsedStorage - info.appUsedStorage).coerceAtLeast(0L)

    LazyColumn(
        contentPadding = PaddingValues(
            top = paddingValues.calculateTopPadding() + 16.dp,
            bottom = parentPaddingValues.calculateBottomPadding() + 16.dp,
            start = 16.dp,
            end = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            StorageOverviewCard(info = info)
        }

        item {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = "Details",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )

                StorageDetailItem(
                    icon = Icons.Default.Storage,
                    label = "Total Device Storage",
                    value = Formatter.formatShortFileSize(context, info.deviceTotalStorage),
                    color = MaterialTheme.colorScheme.primary
                )
                StorageDetailItem(
                    icon = Icons.Default.SdStorage,
                    label = "Used by Harmony Hub",
                    value = Formatter.formatShortFileSize(context, info.appUsedStorage),
                    color = Color(0xFF6200EE)
                )
                StorageDetailItem(
                    icon = Icons.Default.SdStorage,
                    label = "Other Used Storage",
                    value = Formatter.formatShortFileSize(context, otherUsedSize),
                    color = Color(0xFFFFB74D)
                )
                StorageDetailItem(
                    icon = Icons.Default.SdStorage,
                    label = "Available Storage",
                    value = Formatter.formatShortFileSize(context, info.deviceAvailableStorage),
                    color = Color(0xFF81C784)
                )
            }
        }

        item {
            StorageClearDataAction(onClearData = { viewModel.clearAppData() })
        }
    }
}
