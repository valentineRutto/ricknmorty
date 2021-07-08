package com.valentinerutto.ricknmorty

import android.app.Application
import com.valentinerutto.ricknmorty.di.module.appModule
import com.valentinerutto.ricknmorty.di.module.repoModule
import com.valentinerutto.ricknmorty.di.module.viewModelModule
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

    companion object {
        open val context = appModule

    }
}
