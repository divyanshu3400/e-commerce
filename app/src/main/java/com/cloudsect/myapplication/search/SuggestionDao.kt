package com.cloudsect.myapplication.search

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SuggestionDao {

    @Query("SELECT * FROM suggestion_table WHERE suggestions LIKE :queryText")
    fun getSuggestions(queryText: String): List<SuggestionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSuggestion(suggestion: SuggestionEntity)
}