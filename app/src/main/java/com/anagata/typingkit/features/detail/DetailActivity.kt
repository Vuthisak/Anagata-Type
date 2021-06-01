package com.anagata.typingkit.features.detail

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.anagata.typingkit.base.ContainFragmentActivity
import com.anagata.typingkit.repository.model.Font

class DetailActivity : ContainFragmentActivity() {

    override val fragment: Fragment = DetailFragment()

    companion object {
        const val FONT_KEY = "fontKey"

        fun start(context: Context, font: Font) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(FONT_KEY, font)
            context.startActivity(intent)
        }
    }

}
