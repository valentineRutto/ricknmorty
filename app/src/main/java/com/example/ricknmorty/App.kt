package com.example.ricknmorty

import android.app.Application
import com.example.ricknmorty.data.di.module.appModule
import com.example.ricknmorty.data.di.module.repoModule
import com.example.ricknmorty.data.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, repoModule, viewModelModule))
        }
    }
}
