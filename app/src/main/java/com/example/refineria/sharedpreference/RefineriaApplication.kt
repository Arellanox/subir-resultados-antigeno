package com.example.refineria.sharedpreference

import android.app.Application

class RefineriaApplication : Application() {

    companion object {

        lateinit var prefs:Prefs
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Prefs(applicationContext)
    }
}