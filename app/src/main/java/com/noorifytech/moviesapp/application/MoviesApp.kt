package com.noorifytech.moviesapp.application

import android.app.Application
import com.noorifytech.moviesapp.BuildConfig
import timber.log.Timber

class MoviesApp : Application() {
    override fun onCreate() {
        super.onCreate()

        instance = this

        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    companion object {
        var instance: Application? = null
    }
}