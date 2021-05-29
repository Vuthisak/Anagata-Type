package com.anagata.typingkit.repository.model

import java.io.Serializable

class Typeface : Serializable {
    val typefaces: ArrayList<Font> = arrayListOf()

    val fontNames: ArrayList<String>
        get() {
            val strings = arrayListOf<String>()
            typefaces.forEach {
                strings.add(it.name)
            }
            return strings
        }

}
