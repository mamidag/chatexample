package com.example.meditopitestcase

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import com.github.ajalt.timberkt.Timber



@HiltAndroidApp
class MeditopiaCase : Application(){
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}