package com.example.harmonyhub.core.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun ErrorView(onRefresh: () -> Unit,message: String = "Some Error Occured",paddingValues: PaddingValues) {
    Column(
        Modifier.fillMaxSize().padding(paddingValues).padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp,Alignment.CenterVertically)
    ) {
        Text(message, style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.Center )
        OutlineButton(
            Modifier.clickable(onClick = onRefresh)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)){
                Icon(Icons.Default.Refresh, null)
                Text("Refresh")
            }
        }

    }
}