package com.example.harmonyhub.features.auth.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun UserNameTextField (
    value: String,
    onValueChange:(String)-> Unit
){

    TextField(

        value,onValueChange,
        label = { Text("User Name") },
        modifier =  Modifier.fillMaxWidth(),

    )
}