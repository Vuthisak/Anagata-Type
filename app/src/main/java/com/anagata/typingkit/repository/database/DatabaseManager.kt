package com.anagata.typingkit.repository.database

import android.content.Context
import androidx.room.Room

class DatabaseManager(context: Context) {

    private val db = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()

    val fontDao: FontDao
        get() = db.fontDao()

    companion object {
        private const val DB_NAME = "anagataTypingKit"
    }

}