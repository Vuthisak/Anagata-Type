package com.anagatatype.app.features.ui.main

import androidx.fragment.app.Fragment
import com.anagatatype.app.base.ContainFragmentActivity

class MainActivity : ContainFragmentActivity() {

    override val fragment: Fragment = MainFragment()

}