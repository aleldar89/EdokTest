package com.example.edoktest.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EdokTestApplication : Application() {
    override fun onCreate() {
        super.onCreate()

    }
}