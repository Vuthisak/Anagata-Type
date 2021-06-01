package com.anagata.typingkit.repository.model

import com.anagata.typingkit.util.defaultSize
import java.io.Serializable

data class FontSelected(
    val fontStyle: String,
    private val _fontSize: String,
    val fontIndex: Int,
    val fontSizeIndex: Int,
    val location: String
) : Serializable {

    val fontSize: Float
        get() {
            val split = _fontSize.split(" ")
            if (split.size > 1) {
                return split[0].toFloat()
            }
            return defaultSize
        }

}
