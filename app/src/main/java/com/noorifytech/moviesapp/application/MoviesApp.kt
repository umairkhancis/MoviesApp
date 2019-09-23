package com.noorifytech.moviesapp.application

import android.app.Application
import com.noorifytech.moviesapp.BuildConfig
import timber.log.Timber

class MoviesApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}