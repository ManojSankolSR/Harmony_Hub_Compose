package com.example.harmonyhub.features.auth.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AssistChip
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.harmonyhub.core.models.Language


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageSelector(
    languages: List<Language>,
    setLanguages: (List<Language>) -> Unit,
    expand: Boolean = false,
    showChip: Boolean = true,
    onDismiss: () -> Unit = {}
) {

    var isVisibleInternal by remember {
        mutableStateOf(false)
    }

    val isVisible = isVisibleInternal || expand

    fun closeBottomSheet() {
        isVisibleInternal = false
        onDismiss()
    }

    fun openBottomSheet() {
        isVisibleInternal = true
    }

    if (showChip) {
        AssistChip(
            label = {
                Text(
                    if (languages.isEmpty()) "Select Languages"
                    else if (languages.size == 1) languages.first().name.replaceFirstChar { it.uppercaseChar() }
                    else "${languages.size} Languages",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.labelSmall
                )
            },
            onClick = ::openBottomSheet,
            trailingIcon = { Icon(Icons.Default.ArrowDropDown, contentDescription = "") }
        )
    }

    if (isVisible)
        ModalBottomSheet(
            onDismissRequest = ::closeBottomSheet,
            containerColor = MaterialTheme.colorScheme.background,
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "Preferred Languages",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(16.dp)
                )
                Language.entries.forEach { currLanguage ->
                    val isSelected = languages.contains(currLanguage)
                    ElevatedCard(
                        colors = CardDefaults.cardColors(
                            containerColor =
                                if (isSelected)
                                    MaterialTheme.colorScheme.primaryContainer
                                else
                                    MaterialTheme.colorScheme.surfaceContainerLow
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                val newList = if (isSelected) {
                                    languages.filter { it != currLanguage }
                                } else {
                                    languages + currLanguage
                                }
                                setLanguages(newList)
                            }
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .border(
                                width = if (isSelected) 1.dp else 0.dp,
                                shape = RoundedCornerShape(if (isSelected) 12.dp else 0.dp),
                                color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
                            ),
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                currLanguage.name.replaceFirstChar { it.uppercaseChar() },
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.W500
                            )
                            if (isSelected) {
                                Icon(
                                    Icons.Default.Check,
                                    contentDescription = "Selected",
                                    modifier = Modifier.size(16.dp),
                                )
                            }
                        }
                    }
                }

                Spacer(Modifier.height(20.dp))
            }
        }

}
