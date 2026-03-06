package com.example.harmonyhub.features.auth.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun WelcomeTexts() {

    Column() {
        Text(
            "Harmony",
            style = TextStyle(
                fontSize = 80.sp,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            "Hub",
            style = TextStyle(
                fontSize = 80.sp,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            "Music .",
            style = TextStyle(
                fontSize = 80.sp,
                fontWeight = FontWeight.Bold,

                )
        )

    }
}