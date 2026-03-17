package com.example.harmonyhub.features.settings.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.harmonyhub.core.presentation.viewmodel.AuthViewModel

@Composable
fun LogoutItem(
    authViewModel: AuthViewModel
) {

    fun onLogOut() {
        authViewModel.deleteUser()
    }


    ListItem(
        modifier = Modifier.Companion.clickable(onClick = ::onLogOut),
        leadingContent = {
            Icon(
                Icons.AutoMirrored.Filled.Logout,
                contentDescription = "Log Out"
            )
        },
        headlineContent = { Text("Log Out", style = MaterialTheme.typography.titleMedium) },
        supportingContent = {
            Text(
                "Logging Out Will Result in Deletion of User data",
                style = MaterialTheme.typography.labelLarge
            )
        }
    )
}