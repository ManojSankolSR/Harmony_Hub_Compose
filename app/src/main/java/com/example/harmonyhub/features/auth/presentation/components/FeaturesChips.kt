package com.example.harmonyhub.features.auth.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun FeaturesChips() {

    Column() {
        SuggestionChip(onClick = {}, label = { Text("Unlimited Song Downloads") })
        SuggestionChip(onClick = {}, label = { Text("High-Quality Streaming") })
        Spacer(
            Modifier.width(10.dp)
        )
        SuggestionChip(onClick = {}, label = { Text("Many More") })
    }

}