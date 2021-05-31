package com.anagata.typingkit.di

import com.anagata.typingkit.repository.FirebaseRepository
import com.anagata.typingkit.repository.database.DatabaseManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repository = module {
    single {
        DatabaseManager(androidContext())
    }

    single {
        FirebaseRepository(androidContext(), get())
    }
}