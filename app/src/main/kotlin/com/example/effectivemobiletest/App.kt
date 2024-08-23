package com.example.effectivemobiletest

import android.app.Application
import baseModule
import com.example.auth.authModule
import com.example.favourite.favouriteModule
import com.example.search.searchModule
import data.local.localModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                appModule,
                authModule,
                searchModule,
                favouriteModule,
                baseModule,
                localModule
            )
        }
    }
}
