package com.anagata.typingkit

import android.app.Application
import com.anagata.typingkit.di.repository
import com.anagata.typingkit.di.viewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(arrayListOf(repository, viewModel))
        }

    }

}