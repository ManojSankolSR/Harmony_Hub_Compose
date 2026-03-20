package com.example.harmonyhub.features.about.presentation.components

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Terminal
import androidx.compose.material.icons.rounded.VolunteerActivism
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri

@Composable
fun ActionButtons() {
    val context = LocalContext.current
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SocialButton(
            modifier = Modifier.weight(1f),
            text = "GitHub",
            icon = Icons.Rounded.Terminal,
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(8.dp),
            contentColor = MaterialTheme.colorScheme.onSurface,
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, "https://github.com/ManojSankolSR".toUri())
                context.startActivity(intent)
            }
        )

        SocialButton(
            modifier = Modifier.weight(1f),
            text = "Support",
            icon = Icons.Rounded.VolunteerActivism,
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, "https://buymeacoffee.com/Manoj_sankol_sr".toUri())
                context.startActivity(intent)
            }
        )
    }
}
