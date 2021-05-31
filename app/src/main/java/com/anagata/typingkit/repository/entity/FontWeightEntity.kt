package com.anagata.typingkit.repository.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anagata.typingkit.repository.model.FontWeight
import java.util.*

data class FontWeightEntity(
    val name: String,
    val src: String,
    val weight: String,
    val location: String?
) {

    fun getModel(): FontWeight {
        val fontWeight = FontWeight()
        fontWeight.name = name
        fontWeight.src = src
        fontWeight.weight = weight
        fontWeight.location = location
        return fontWeight
    }

}