package com.cloudsect.myapplication.search


class SearchRepository(private val suggestionDao: SuggestionDao, private val apiService: ApiService) {
    fun getCombinedSuggestions(query: String): List<SuggestionEntity> {
        //        val apiSuggestions = apiService.getSuggestions(query)
//        return (localSuggestions.map { it.suggestionText } + apiSuggestions).distinct()
        return suggestionDao.getSuggestions("%$query%")
    }
}
