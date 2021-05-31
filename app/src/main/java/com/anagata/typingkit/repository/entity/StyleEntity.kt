package com.anagata.typingkit.repository.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anagata.typingkit.repository.model.Style

data class StyleEntity(
    @Embedded(prefix = "w300")
    val w300: FontWeightEntity? = null,
    @Embedded(prefix = "w400")
    val w400: FontWeightEntity? = null,
    @Embedded(prefix = "w700")
    val w700: FontWeightEntity? = null
) {

    fun getModel(): Style {
        val style = Style()
        style.w300 = w300?.getModel()
        style.w400 = w400?.getModel()
        style.w700 = w700?.getModel()
        return style
    }

}
