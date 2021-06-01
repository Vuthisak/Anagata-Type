package com.anagata.typingkit.repository.model

import androidx.room.Index
import com.anagata.typingkit.util.getDefaultValue
import java.io.Serializable

class Typeface : Serializable {
    val typefaces: ArrayList<Font> = arrayListOf()

    companion object {
        fun getFontNames(fonts: List<Font>): ArrayList<Triple<String, String, Int>> {
            val triple = arrayListOf<Triple<String, String, Int>>()
            fonts.forEachIndexed { index, font ->
                font.styles.forEach { style ->
                    triple.add(
                        Triple(
                            "${font.name} ${style.name}",
                            style.location.getDefaultValue(),
                            index
                        )
                    )
                }
            }
            return triple
        }
    }

}
