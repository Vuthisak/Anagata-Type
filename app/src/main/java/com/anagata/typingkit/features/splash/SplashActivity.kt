package com.anagata.typingkit.features.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.anagata.typingkit.R
import com.anagata.typingkit.features.main.MainActivity
import com.anagata.typingkit.features.main.MainState
import com.anagata.typingkit.repository.model.Typeface

class SplashActivity : AppCompatActivity() {

    private val vm: SplashViewModel by lazy {
        ViewModelProvider(this)[SplashViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        vm.getData()
        registerObserver()
    }

    private fun openMain(typeface: Typeface? = null) {
        val i = Intent(this@SplashActivity, MainActivity::class.java)
        typeface?.let { i.putExtra(KEY_TYPEFACE, typeface) }
        startActivity(i)
        finish()
    }

    private fun registerObserver() {
        vm.liveData.observe(this, {
            when (it) {
                is MainState.OnGetTypeFaceSuccess -> {
                    openMain(it.typeface)
                }
            }
        })
    }

    companion object {
        const val KEY_TYPEFACE = "typefaceKey"
    }

}