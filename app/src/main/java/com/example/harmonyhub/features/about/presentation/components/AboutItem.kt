package com.example.harmonyhub.features.about.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun AboutItem(
    onClick: () -> Unit
) {
    ListItem(
        modifier = Modifier.clickable(onClick = onClick),
        leadingContent = {
            Icon(
                Icons.Rounded.Info,
                contentDescription = "About"
            )
        },
        headlineContent = { Text("About", style = MaterialTheme.typography.titleSmall) },
        supportingContent = {
            Text(
                "Learn more about Harmony Hub",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.W400
            )
        }
    )
}
