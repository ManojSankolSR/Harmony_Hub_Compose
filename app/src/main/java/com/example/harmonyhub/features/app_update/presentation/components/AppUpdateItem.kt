package com.example.harmonyhub.features.app_update.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.SystemUpdate
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun AppUpdateItem(
    onClick: () -> Unit
) {
    ListItem(
        modifier = Modifier.clickable(onClick = onClick),
        leadingContent = {
            Icon(
                Icons.Rounded.SystemUpdate,
                contentDescription = "Check for Updates"
            )
        },
        headlineContent = { Text("Check for Updates", style = MaterialTheme.typography.titleSmall) },
        supportingContent = {
            Text(
                "Keep Harmony Hub up to date",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.W400
            )
        }
    )
}
