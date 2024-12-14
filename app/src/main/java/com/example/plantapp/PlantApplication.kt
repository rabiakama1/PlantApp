package com.example.plantapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PlantApplication : Application() {
    /** görevi application olduğunu bildirmek */

    override fun onCreate() {
        super.onCreate()
    }

}