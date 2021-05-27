package com.anagata.typingkit.features.ui.main

import androidx.fragment.app.Fragment
import com.anagata.typingkit.base.ContainFragmentActivity
import com.anagata.typingkit.features.ui.main.fragment.MainFragment

class MainActivity : ContainFragmentActivity() {

    override val fragment: Fragment = MainFragment()

}