package com.anagata.typingkit.repository.model

import com.anagata.typingkit.repository.entity.FontEntity
import java.io.Serializable

class Font : Serializable {
    var name: String = ""
    var styles = listOf<Style>()

    val entity: FontEntity
        get() = FontEntity(name, FontEntity.listToJson(styles))
}
