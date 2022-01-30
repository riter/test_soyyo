package com.desarrollandoapp.testsoyyo

import android.app.Application
import com.facebook.stetho.BuildConfig
import com.facebook.stetho.Stetho

class TestSoyYoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            setupStetho()
        }
    }

    private fun setupStetho() {
        Stetho.initializeWithDefaults(this)
    }
}