package com.anagata.typingkit.features.main

import com.anagata.typingkit.repository.model.Typeface

sealed class MainState {
    object LOADING
    object FINISHED
    data class OnGetTypeFaceSuccess(val typeface: Typeface) : MainState()
    data class OnError(val errorMessage: String) : MainState()
}