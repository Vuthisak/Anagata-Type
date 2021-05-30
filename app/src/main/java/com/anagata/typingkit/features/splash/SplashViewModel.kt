package com.anagata.typingkit.features.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anagata.typingkit.features.main.MainState
import com.anagata.typingkit.repository.FirebaseRepository

class SplashViewModel(
) : ViewModel() {

    val liveData: MutableLiveData<MainState> by lazy {
        MutableLiveData<MainState>()
    }

    private val firebaseRepository: FirebaseRepository = FirebaseRepository()

    fun getData() {
        firebaseRepository.getData({
            liveData.value = MainState.OnGetTypeFaceSuccess(it)
        }, {
            MainState.OnError(it.toString())
        })
    }

}
