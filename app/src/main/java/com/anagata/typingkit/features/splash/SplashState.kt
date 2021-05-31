package com.anagata.typingkit.features.splash

sealed class SplashState {
    object DOWNLOADED : SplashState()
    object FAILED : SplashState()
}
