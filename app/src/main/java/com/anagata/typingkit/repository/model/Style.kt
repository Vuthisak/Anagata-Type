package com.anagata.typingkit.repository.model

import com.anagata.typingkit.repository.entity.StyleEntity
import java.io.Serializable

class Style : Serializable {
    var w300: FontWeight? = null

    var w400: FontWeight? = null

    var w700: FontWeight? = null

    val entity: StyleEntity
        get() = StyleEntity(w300?.entity, w400?.entity, w700?.entity)

}
