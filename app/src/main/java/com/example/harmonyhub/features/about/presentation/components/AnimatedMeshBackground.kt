package com.example.harmonyhub.features.about.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MeshBackground() {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .size(650.dp)
                .offset(x = (-150).dp, y = (-280).dp)
                .blur(150.dp)
                .alpha(0.18f)
                .background(
                    Brush.radialGradient(
                        colors = listOf(MaterialTheme.colorScheme.primary, Color.Transparent)
                    ),
                    CircleShape
                )
        )
        Box(
            modifier = Modifier
                .size(500.dp)
                .align(Alignment.BottomEnd)
                .offset(x = 200.dp, y = 200.dp)
                .blur(120.dp)
                .alpha(0.15f)
                .background(
                    Brush.radialGradient(
                        colors = listOf(MaterialTheme.colorScheme.tertiary, Color.Transparent)
                    ),
                    CircleShape
                )
        )
    }
}
