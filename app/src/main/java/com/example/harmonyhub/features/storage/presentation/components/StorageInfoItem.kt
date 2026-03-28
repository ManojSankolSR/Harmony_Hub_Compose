package com.example.harmonyhub.features.storage.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.example.harmonyhub.navigation.bottom_bar_nav.settings_nav.SettingsNavRoutes


@Composable
fun StorageInfoItem(navController: NavHostController) {

    fun onPress() {
        navController.navigate(SettingsNavRoutes.StorageInfoScreen)
    }


    ListItem(
        modifier = Modifier.clickable(onClick = ::onPress),
        leadingContent = {
            Icon(
                Icons.Default.Storage,
                contentDescription = "Storage Information"
            )
        },
        headlineContent = {
            Text(
                "Storage Information",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        },
        supportingContent = {
            Text(
                "View device storage usage and manage app data",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.W400
            )
        },
    )
}
