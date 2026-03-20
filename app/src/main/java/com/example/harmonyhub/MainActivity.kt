package com.example.harmonyhub

import android.content.ComponentName
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.example.harmonyhub.navigation.root_nav.RootNavGraph
import com.example.harmonyhub.ui.theme.HarmonyHubTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            HarmonyHubTheme {
                RootNavGraph()
            }
        }
    }
}
