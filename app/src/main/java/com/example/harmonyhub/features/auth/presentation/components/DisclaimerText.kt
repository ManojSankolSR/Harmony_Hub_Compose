package com.example.harmonyhub.features.auth.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight


@Composable
fun DisclaimerText() {
    Text("Disclaimer: We Respect your privacy more than anything else, All your details will be Stored locally on your Device ", style = TextStyle(
        color = Color.Gray,
        fontWeight = FontWeight.W300,
    ))
}