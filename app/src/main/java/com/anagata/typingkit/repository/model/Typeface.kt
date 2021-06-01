package com.anagata.typingkit.repository.model

import com.anagata.typingkit.util.getDefaultValue
import java.io.Serializable

class Typeface : Serializable {
    val typefaces: ArrayList<Font> = arrayListOf()

    companion object {
        fun getFontNames(fonts: List<Font>): ArrayList<Pair<String, String>> {
            val strings = arrayListOf<Pair<String, String>>()
            fonts.forEach { font ->
                font.styles.forEach { style ->
                    strings.add(
                        Pair(
                            "${font.name} ${style.name}",
                            style.location.getDefaultValue()
                        )
                    )
                }
            }
            return strings
        }
    }

}
