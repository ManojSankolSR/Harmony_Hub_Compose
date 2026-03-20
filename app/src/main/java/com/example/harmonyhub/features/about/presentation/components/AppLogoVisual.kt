package com.example.harmonyhub.features.about.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.harmonyhub.R

@Composable
fun AppLogoVisual() {
    val isDark = isSystemInDarkTheme()
    val primaryGlowAlpha = if (isDark) 0.35f else 0.22f
    val tertiaryGlowAlpha = if (isDark) 0.2f else 0.12f

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(200.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            // Multi-layered glow
            Box(modifier = Modifier.size(130.dp).blur(45.dp).alpha(primaryGlowAlpha).background(MaterialTheme.colorScheme.primary, CircleShape))
            Box(modifier = Modifier.size(100.dp).blur(30.dp).alpha(tertiaryGlowAlpha).background(MaterialTheme.colorScheme.tertiary, CircleShape))

            // Main Glass Container
            Surface(
                modifier = Modifier
                    .size(140.dp)
                    .border(
                        BorderStroke(
                            1.5.dp, 
                            Brush.linearGradient(
                                listOf(
                                    if (isDark) Color.White.copy(alpha = 0.4f) else MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                                    Color.Transparent, 
                                    if (isDark) Color.White.copy(alpha = 0.1f) else MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                                )
                            )
                        ),
                        RoundedCornerShape(44.dp)
                    ),
                shape = RoundedCornerShape(44.dp),
                color = MaterialTheme.colorScheme.surfaceColorAtElevation(if (isDark) 12.dp else 4.dp).copy(alpha = if (isDark) 0.85f else 0.95f),
                tonalElevation = if (isDark) 15.dp else 2.dp,
                shadowElevation = if (isDark) 30.dp else 12.dp
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        painter = painterResource(R.drawable.app_icon),
                        contentDescription = null,
                        modifier = Modifier.size(76.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}
