package com.example.harmonyhub

import android.app.Application
import com.example.harmonyhub.core.data.local.db.AppDatabase
import com.example.harmonyhub.di.AppContainer

class HarmonyHub : Application() {
    lateinit var appContainer: AppContainer;
    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer(this)
    }
}