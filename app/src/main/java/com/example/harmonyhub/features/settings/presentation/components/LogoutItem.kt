package com.example.harmonyhub.features.settings.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.harmonyhub.features.auth.presentation.viewmodel.AuthViewModel

@Composable
fun LogoutItem(
    authViewModel: AuthViewModel
) {
    var showLogoutDialog by remember { mutableStateOf(false) }

    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            title = { Text("Log Out") },
            text = { Text("Logging out will result in the deletion of all user data. Are you sure you want to proceed?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        authViewModel.deleteUser()
                        showLogoutDialog = false
                    },
                    colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Log Out")
                }
            },
            dismissButton = {
                TextButton(onClick = { showLogoutDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    ListItem(
        modifier = Modifier.clickable(onClick = { showLogoutDialog = true }),
        leadingContent = {
            Icon(
                Icons.AutoMirrored.Filled.Logout,
                contentDescription = "Log Out"
            )
        },
        headlineContent = { Text("Log Out", style = MaterialTheme.typography.titleSmall) },
        supportingContent = {
            Text(
                "Sign out and delete all local data",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.W400
            )
        },
        colors = ListItemDefaults.colors(
//            containerColor = MaterialTheme.colorScheme.errorContainer,
            headlineColor = MaterialTheme.colorScheme.error,
            supportingColor = MaterialTheme.colorScheme.error,
            leadingIconColor = MaterialTheme.colorScheme.error
        )
    )
}
