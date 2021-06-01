package com.anagata.typingkit.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anagata.typingkit.repository.entity.FontEntity

@Database(entities = [FontEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun fontDao(): FontDao
}
