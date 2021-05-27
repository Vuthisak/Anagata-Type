package com.anagata.typingkit.features.detail.activity

import androidx.fragment.app.Fragment
import com.anagata.typingkit.base.ContainFragmentActivity
import com.anagata.typingkit.features.detail.fragment.DetailFragment

class DetailActivity : ContainFragmentActivity() {

    override val fragment: Fragment = DetailFragment()

}
