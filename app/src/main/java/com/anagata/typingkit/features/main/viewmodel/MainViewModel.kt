package com.anagata.typingkit.features.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anagata.typingkit.features.main.state.MainState
import com.anagata.typingkit.repository.FirebaseRepository

class MainViewModel(
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
