package com.example.harmonyhub.features.settings.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.harmonyhub.core.models.Language
import com.example.harmonyhub.core.presentation.viewmodel.AuthViewModel
import com.example.harmonyhub.features.auth.presentation.components.LanguageSelector

@Composable
fun PreferredLanguageItem(
    authViewModel: AuthViewModel
) {

    val authState by authViewModel.state.collectAsState()

    val user = authState.user ?: return

    val langs = user.preferredLanguage

    fun onLanguageChange(langs: List<Language>) {
        authViewModel.updatePreferredLanguage(langs)
    }


    ListItem(
        modifier = Modifier.Companion.clickable {},
        leadingContent = {
            Icon(
                Icons.Default.GraphicEq,
                contentDescription = "Preferred Language"
            )
        },
        trailingContent = {
            LanguageSelector(
                languages = langs,
                setLanguages = ::onLanguageChange
            )
        },
        headlineContent = {
            Text(
                "Preferred Language",
                style = MaterialTheme.typography.titleMedium
            )
        },
        supportingContent = {
            Text(
                "Content will be recommended in :- ${langs.joinToString(", ") { it.name.replaceFirstChar { char -> char.uppercase() } }}",
                style = MaterialTheme.typography.labelLarge
            )
        }
    )
}