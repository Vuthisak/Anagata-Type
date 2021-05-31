package com.anagata.typingkit.features.main

import com.anagata.typingkit.repository.model.Font

sealed class MainState {
    object LOADING : MainState()
    object FINISHED : MainState()
    data class OnGetTypeFaceSuccess(val fonts: List<Font>) : MainState()
    data class OnError(val errorMessage: String) : MainState()
}