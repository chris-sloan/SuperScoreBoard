package com.chrissloan.superscoreboard.application

import android.app.Application
import com.chrissloan.superscoreboard.data.Dependencies.dataDependencies
import com.chrissloan.superscoreboard.data.Dependencies.uiDependencies
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class ScoreBoardApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ScoreBoardApplication)
            modules(
                dataDependencies,
                uiDependencies
            )
        }
    }
}