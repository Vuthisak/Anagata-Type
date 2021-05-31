package com.anagata.typingkit.repository.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.anagata.typingkit.repository.entity.FontEntity

@Dao
interface FontDao {

    @Insert
    fun insert(fontEntity: FontEntity)

    @Query("SELECT * FROM FontEntity WHERE name IN (:name)")
    fun getByName(name: String): FontEntity?

    @Query("SELECT * FROM FontEntity")
    fun getAllFonts(): List<FontEntity>?

}