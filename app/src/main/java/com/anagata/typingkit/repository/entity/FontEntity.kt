package com.anagata.typingkit.repository.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anagata.typingkit.repository.model.Font
import com.anagata.typingkit.repository.model.Style
import com.google.gson.Gson

@Entity
data class FontEntity(
    val name: String,
    val styles: String,
    @PrimaryKey(autoGenerate = true)
    val fontId: Long = 0
) {
    fun getModel(): Font {
        val font = Font()
        font.name = name
        font.styles = jsonToList(styles)
        return font
    }

    companion object {
        private val gson = Gson()

        fun listToJson(styles: List<Style>): String = gson.toJson(styles)
        fun jsonToList(jsonStyle: String) =
            gson.fromJson(jsonStyle, Array<Style>::class.java).toList()

        fun translateFontList(fontEntities: List<FontEntity>): List<Font> {
            val fonts = arrayListOf<Font>()
            fontEntities.forEach {
                fonts.add(it.getModel())
            }
            return fonts
        }
    }

}