package com.anagata.typingkit.features.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anagata.typingkit.repository.FirebaseRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel(
    private val firebaseRepository: FirebaseRepository
) : ViewModel() {

    val liveData: MutableLiveData<MainState> by lazy {
        MutableLiveData<MainState>()
    }

    fun getFonts() {
        firebaseRepository.getAllFontsFromDb {
            if (it.isEmpty()) {
                getFonts()
            } else {
                liveData.postValue(MainState.OnGetTypeFaceSuccess(it))
            }
        }
    }

}
