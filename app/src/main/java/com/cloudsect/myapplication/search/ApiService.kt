package com.cloudsect.myapplication.search

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search/suggestions")
    suspend fun getSuggestions(@Query("query") query: String): List<SuggestionEntity>
}
