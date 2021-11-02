package com.example.anadolubankproject

import android.content.Context
import android.util.Log
import androidx.multidex.MultiDex
import com.example.anadolubankproject.di.DaggerAppComponent
import com.example.anadolubankproject.model.room.DatabaseManager
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class AppControl : DaggerApplication() {
    companion object {
        lateinit var instance: AppControl
            private set
    }
    var handler: Thread.UncaughtExceptionHandler? = null
    var appDb: DatabaseManager? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        handler = Thread.UncaughtExceptionHandler { t, e ->
            e.printStackTrace()
            Log.i("", "")
        }
        Thread.setDefaultUncaughtExceptionHandler(handler)
        appDb = DatabaseManager.getDatabaseManager(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().create(this)

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}