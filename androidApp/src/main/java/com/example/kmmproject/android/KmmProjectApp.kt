package com.example.kmmproject.android

import android.app.Application
import com.example.kmmproject.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext

class KmmProjectApp : Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidLogger()// check realisation and provide custom class
            androidContext(this@KmmProjectApp)
            modules(appModule())
        }
    }
}