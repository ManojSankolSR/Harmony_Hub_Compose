package com.example.harmonyhub.features.about.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.harmonyhub.features.about.presentation.components.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    paddingValues: PaddingValues,
    onBack: () -> Unit
) {
    val infoItems = listOf(
        Triple(
            "Modern & Fluid UI",
            Icons.Rounded.Dashboard,
            "A seamless experience built with Jetpack Compose. Harmony Hub features a dynamic Material 3 interface that adapts to your rhythm, ensuring a clean, bloat-free, and expressive environment."
        ),
        Triple(
            "Offline Freedom",
            Icons.Rounded.DownloadDone,
            "Your music, anywhere. With our advanced download manager and local library system, you can access your favorite songs anytime, anywhere"
        ),
        Triple(
            "The Developer",
            Icons.Rounded.Person,
            "Crafted by Manoj Sankol S R. Harmony Hub is a solo project born from a passion for high-end Android aesthetics and robust, open-source functionality."
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("")
                },
                navigationIcon = {
                    FilledTonalIconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = Color.Transparent
                )
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            // Static Mesh Background
            MeshBackground()

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                contentPadding = PaddingValues(
                    top = padding.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding() + 40.dp
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Box(
                        modifier = Modifier.height(220.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        AppLogoVisual()
                    }
                }

                item {
                    BrandingHeader()
                    Spacer(modifier = Modifier.height(48.dp))
                }

                itemsIndexed(infoItems) { _, (title, icon, content) ->
                    InfoCard(title, icon, content)
                }

                item {
                    Spacer(modifier = Modifier.height(32.dp))
                    ActionButtons()
                }

                item {
                    Spacer(modifier = Modifier.height(64.dp))
                    FooterSection()
                }
            }
        }
    }
}
