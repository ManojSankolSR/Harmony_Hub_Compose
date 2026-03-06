package com.example.harmonyhub.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp


@Composable
fun OutlineButton(modifier: Modifier = Modifier, content: @Composable ()->Unit) {
    Box(
        modifier
            .border(
                .6.dp,
                MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(6.dp)
            )
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(12.dp, vertical = 4.dp)


    ) {
        content()
    }
}