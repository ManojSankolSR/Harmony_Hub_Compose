package com.example.harmonyhub.features.auth.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.harmonyhub.core.models.AudioQuality
import com.example.harmonyhub.core.models.Language
import com.example.harmonyhub.core.models.User
import com.example.harmonyhub.core.presentation.viewmodel.AuthViewModel


@Composable
fun GetStartedButton(userName: String,audioQuality: AudioQuality,languages: List<Language>,viewModel: AuthViewModel){

    val state by viewModel.state.collectAsState()

    val onClick:()-> Unit={


        val user= User(
            id = 0,
            name = userName,
            preferredLanguage = languages,
            preferredAudioQuality = audioQuality
        )

        viewModel.addOrUpdateUser(user)
    }

    ElevatedButton (
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        enabled = !state.isLoading && userName.isNotEmpty(),
        modifier = Modifier.fillMaxWidth().height( 50.dp),
        onClick = onClick

    ) {
        Text( state.error ?: "Get Started", style = MaterialTheme.typography.titleMedium)
    }
}