package com.example.harmonyhub.features.settings.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.harmonyhub.core.presentation.viewmodel.AuthViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(paddingValues: PaddingValues,    navController: NavHostController,authViewModel: AuthViewModel,) {

    val logOut:()->Unit={
        authViewModel.deleteUser();
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar(title = {Text("Settings")}) }
    ) { it->
        Column(Modifier.padding(it)) {
            ListItem(
                modifier = Modifier.clickable{
                    logOut();
                },
                leadingContent = { Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = "LogOut") },
                headlineContent = { Text("Log Out", style = MaterialTheme.typography.titleMedium) },
                supportingContent = {Text("Logging Out Will Result in Deletion of User data",style = MaterialTheme.typography.labelLarge)}
            )

        }
    }



}