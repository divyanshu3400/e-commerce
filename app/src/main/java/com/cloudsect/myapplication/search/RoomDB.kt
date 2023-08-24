package com.cloudsect.myapplication.search

import android.content.Context
import androidx.room.Room.databaseBuilder

object RoomDB {
    private var appDatabase: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {
        if (appDatabase == null) {
            appDatabase = databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "app-database"
            )
                .allowMainThreadQueries()
                .build()
        }
        return appDatabase!!
    }
}
