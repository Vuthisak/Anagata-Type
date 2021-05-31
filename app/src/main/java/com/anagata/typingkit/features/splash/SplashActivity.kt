package com.anagata.typingkit.features.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.anagata.typingkit.R
import com.anagata.typingkit.features.main.MainActivity
import com.anagata.typingkit.features.main.MainState
import com.anagata.typingkit.repository.model.Typeface
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private val vm: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        vm.getData()
        registerObserver()
    }

    private fun registerObserver() {
        vm.liveData.observe(this, {
            when (it) {
                is SplashState.DOWNLOADED, SplashState.FAILED -> openMain()
            }
        })
    }

    private fun openMain() {
        val i = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(i)
        finish()
    }

    companion object {
        const val KEY_TYPEFACE = "typefaceKey"
    }

}