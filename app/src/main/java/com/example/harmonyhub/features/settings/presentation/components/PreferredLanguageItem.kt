package com.example.harmonyhub.features.settings.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.harmonyhub.core.models.Language
import com.example.harmonyhub.features.auth.presentation.viewmodel.AuthViewModel
import com.example.harmonyhub.features.auth.presentation.components.LanguageSelector

@Composable
fun PreferredLanguageItem(
    authViewModel: AuthViewModel
) {

    val authState by authViewModel.state.collectAsState()

    val user = authState.user ?: return

    val langs = user.preferredLanguage

    var isSelectorVisible by remember { mutableStateOf(false) }

    fun onLanguageChange(langs: List<Language>) {
        authViewModel.updatePreferredLanguage(langs)
    }

    LanguageSelector(
        languages = langs,
        setLanguages = ::onLanguageChange,
        expand = isSelectorVisible,
        showChip = false,
        onDismiss = { isSelectorVisible = false }
    )

    val languagesText = if (langs.isEmpty()) {
        "None selected"
    } else {
        langs.joinToString(", ") { it.name.replaceFirstChar { char -> char.uppercaseChar() } }
    }

    ListItem(
        modifier = Modifier.clickable { isSelectorVisible = true },
        leadingContent = {
            Icon(
                imageVector = Icons.Default.Translate,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(28.dp)
            )
        },
        headlineContent = {
            Text(
                text = "Preferred Language",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        },
        supportingContent = {
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    text = languagesText,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold
                    ),
                    maxLines = 1,
                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                )
                Text(
                    text = "Content will be recommended in Selected Languages",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
            }
        },
        trailingContent = {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
            )
        }
    )
}
