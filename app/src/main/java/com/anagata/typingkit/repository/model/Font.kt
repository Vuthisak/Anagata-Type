package com.anagata.typingkit.repository.model

import com.anagata.typingkit.repository.entity.FontEntity
import java.io.Serializable

class Font : Serializable {
    var name: String = ""
    var styles: Style? = null

    val entity: FontEntity
        get() = FontEntity(name, styles?.entity)
}
