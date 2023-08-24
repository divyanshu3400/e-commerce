package com.cloudsect.myapplication.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: SearchRepository) : ViewModel() {
//    private val _suggestions = MutableLiveData<List<SuggestionEntity>>()
    private lateinit var suggestions: List<SuggestionEntity>

    fun fetchSuggestions(query: String): List<SuggestionEntity> {
        viewModelScope.launch {
            val combinedSuggestions = repository.getCombinedSuggestions(query)
//            _suggestions.postValue(combinedSuggestions)
            suggestions=combinedSuggestions
        }
        return suggestions
    }
}
