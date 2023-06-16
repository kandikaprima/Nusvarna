package com.dicoding.picodiploma.nusvarna.utils

import android.app.Application
import android.content.Context

class MyApplication : Application() {
    companion object {
        lateinit var appContext: Context
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}