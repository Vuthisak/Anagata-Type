package com.anagata.typingkit.features.main

import androidx.fragment.app.Fragment
import com.anagata.typingkit.base.ContainFragmentActivity

class MainActivity : ContainFragmentActivity() {

    override val fragment: Fragment = MainFragment()

}