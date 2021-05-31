package com.anagata.typingkit.repository.model

import java.io.Serializable

class Typeface : Serializable {
    val typefaces: ArrayList<Font> = arrayListOf()

    companion object {
        fun getFontNames(fonts: List<Font>): ArrayList<String> {
            val strings = arrayListOf<String>()
            fonts.forEach {
                strings.add(it.name)
            }
            return strings
        }
    }

}
