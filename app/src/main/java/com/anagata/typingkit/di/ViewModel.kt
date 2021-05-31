package com.anagata.typingkit.di

import com.anagata.typingkit.features.main.MainViewModel
import com.anagata.typingkit.features.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {

    viewModel {
        SplashViewModel(get())
    }

    viewModel {
        MainViewModel(get())
    }

}
