package com.anagata.typingkit.repository.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomWarnings
import com.anagata.typingkit.repository.model.Font
import java.util.*
import kotlin.collections.ArrayList

@Entity
data class FontEntity(
    val name: String,
    @Embedded val styles: StyleEntity? = null,
    @PrimaryKey(autoGenerate = true)
    val fontId: Long = 0
) {

    fun getModel(): Font {
        val font = Font()
        font.name = name
        font.styles = styles?.getModel()
        return font
    }

    companion object {
        fun translateFontList(fontEntities: List<FontEntity>): List<Font> {
            val fonts = arrayListOf<Font>()
            fontEntities.forEach {
                fonts.add(it.getModel())
            }
            return fonts
        }
    }

}