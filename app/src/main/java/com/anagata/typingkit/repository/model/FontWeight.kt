package com.anagata.typingkit.repository.model

import com.anagata.typingkit.repository.entity.FontWeightEntity
import java.io.Serializable

class FontWeight : Serializable {
    var name: String = ""
    var src: String = ""
    var weight: String = ""
    var location: String? = null

    val entity: FontWeightEntity
        get() = FontWeightEntity(name, src, weight, location)

}
