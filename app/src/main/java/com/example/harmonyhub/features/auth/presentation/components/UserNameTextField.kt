package com.example.harmonyhub.features.auth.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.text.input.ImeAction


@Composable
fun UserNameTextField (
    value: String,
    focusManager: FocusManager,
    onValueChange:(String)-> Unit
){

    TextField(
        value,onValueChange,
        label = { Text("User Name") },
        modifier =  Modifier.fillMaxWidth(),

        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),


        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus() // 👈 closes keyboard
            }
        )

    )
}