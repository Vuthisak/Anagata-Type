package com.anagata.typingkit.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.anagata.typingkit.R
import com.anagata.typingkit.databinding.ActivityContainerFragmentBinding

abstract class ContainFragmentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContainerFragmentBinding

    abstract val fragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        addFragment(fragment)
    }

    private fun setupBinding() {
        binding = ActivityContainerFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, fragment)
            .commit()
    }

}