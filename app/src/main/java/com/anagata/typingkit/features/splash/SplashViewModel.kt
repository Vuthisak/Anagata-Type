package com.anagata.typingkit.features.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anagata.typingkit.features.main.MainState
import com.anagata.typingkit.repository.FirebaseRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SplashViewModel(
    private val firebaseRepository: FirebaseRepository
) : ViewModel() {

    val liveData: MutableLiveData<SplashState> by lazy {
        MutableLiveData<SplashState>()
    }

    fun getData() {
        firebaseRepository.initData({
            liveData.postValue(SplashState.DOWNLOADED)
        }, {
            liveData.postValue(SplashState.FAILED)
        })
    }

}
