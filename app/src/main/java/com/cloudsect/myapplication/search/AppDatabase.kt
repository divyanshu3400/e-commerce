package com.cloudsect.myapplication.search

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [SuggestionEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun suggestionDao(): SuggestionDao
}
