package com.example.harmonyhub.features.auth.presentation.screens

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.harmonyhub.core.models.AudioQuality
import com.example.harmonyhub.core.models.Language
import com.example.harmonyhub.features.auth.presentation.viewmodel.AuthViewModel
import com.example.harmonyhub.features.auth.presentation.components.AudioQualitySelector
import com.example.harmonyhub.features.auth.presentation.components.DisclaimerText
import com.example.harmonyhub.features.auth.presentation.components.FeaturesChips
import com.example.harmonyhub.features.auth.presentation.components.GetStartedButton
import com.example.harmonyhub.features.auth.presentation.components.LanguageSelector
import com.example.harmonyhub.features.auth.presentation.components.UserNameTextField
import com.example.harmonyhub.features.auth.presentation.components.WelcomeTexts


@Composable
fun LoginScreen(viewModel: AuthViewModel) {

    val focusManager = LocalFocusManager.current

    var userName by remember {
        mutableStateOf("")
    }

    var audioQuality by remember {
        mutableStateOf(AudioQuality.medium)
    }

    var languages by remember {
        mutableStateOf<List<Language>>(emptyList())
    }

    Scaffold { paddingValues ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                Modifier
                    .padding(horizontal = 25.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .imePadding()
                    .pointerInput(Unit) {
                        detectTapGestures {
                            focusManager.clearFocus()
                        }
                    },
                verticalArrangement = Arrangement.Center,
            ) {
                WelcomeTexts()
                Spacer(Modifier.height(10.dp))
                FeaturesChips()
                Spacer(Modifier.height(30.dp))
                UserNameTextField(userName, focusManager = focusManager) { name ->
                    userName = name
                }
                Spacer(Modifier.height(15.dp))
                Row {
                    AudioQualitySelector(
                        audioQuality = audioQuality,
                        setAudioQuality = { quality ->
                            audioQuality = quality
                        }
                    )
                    Spacer(Modifier.width(10.dp))
                    LanguageSelector(
                        languages = languages,
                        setLanguages = { selLanguage ->
                            languages = selLanguage
                        }
                    )
                }
                Spacer(Modifier.height(30.dp))
                GetStartedButton(userName, audioQuality, languages, viewModel)
                Spacer(Modifier.height(30.dp))
                DisclaimerText()
            }
        }
    }
}
