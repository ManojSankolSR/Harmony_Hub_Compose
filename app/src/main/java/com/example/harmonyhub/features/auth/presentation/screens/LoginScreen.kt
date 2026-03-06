package com.example.harmonyhub.features.auth.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.harmonyhub.HarmonyHub
import com.example.harmonyhub.core.data.local.db.AppDatabase
import com.example.harmonyhub.core.data.respository.UserRepository
import com.example.harmonyhub.core.models.AudioQuality
import com.example.harmonyhub.core.models.Language
import com.example.harmonyhub.core.presentation.viewmodel.AuthViewModel
import com.example.harmonyhub.core.presentation.viewmodel.AuthViewModelFactory
import com.example.harmonyhub.features.auth.presentation.components.AudioQualitySelector
import com.example.harmonyhub.features.auth.presentation.components.DisclaimerText
import com.example.harmonyhub.features.auth.presentation.components.FeaturesChips
import com.example.harmonyhub.features.auth.presentation.components.GetStartedButton
import com.example.harmonyhub.features.auth.presentation.components.LanguageSelector
import com.example.harmonyhub.features.auth.presentation.components.UserNameTextField
import com.example.harmonyhub.features.auth.presentation.components.WelcomeTexts


@Composable
fun LoginScreen(viewModel: AuthViewModel) {

    val app = LocalContext.current.applicationContext as HarmonyHub;

    val userRepository = app.appContainer.userRepository;

    var userName by remember {
        mutableStateOf<String>("")
    }

    var audioQuality by remember {
        mutableStateOf<AudioQuality>(AudioQuality.medium)
    }

    var language by remember {
        mutableStateOf<Language>(Language.kannada)
    }

    Scaffold() {
        Box(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                Modifier
                    .padding(horizontal = 25.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
            ) {
                WelcomeTexts()
                Spacer(Modifier.height(10.dp))
                FeaturesChips()
                Spacer(Modifier.height(30.dp))
                UserNameTextField(userName) { name ->
                    userName = name;
                }
                Spacer(Modifier.height(15.dp))
                Row() {
                    AudioQualitySelector(audioQuality) { quality ->
                        audioQuality = quality
                    }
                    Spacer((Modifier.width(10.dp)))
                    LanguageSelector(language) { selLanguage ->
                        language = selLanguage
                    }
                }
                Spacer(Modifier.height(30.dp))
                GetStartedButton(userName, audioQuality, language, viewModel)
                Spacer(Modifier.height(30.dp))
                DisclaimerText()
            }

        }

    }

}