package com.cloudsect.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AutoCompleteTextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cloudsect.myapplication.adapter.SuggestionAdapter
import com.cloudsect.myapplication.databinding.ActivitySearchableBinding
import com.cloudsect.myapplication.retrofit.ApiService
import com.cloudsect.myapplication.retrofit.RetrofitClient
import com.cloudsect.myapplication.search.RoomDB
import com.cloudsect.myapplication.search.SearchRepository
import com.cloudsect.myapplication.search.SearchViewModel
import com.cloudsect.myapplication.search.SuggestionDao
import com.cloudsect.myapplication.search.SuggestionEntity
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class SearchableActivity : AppCompatActivity(),SuggestionAdapter.OnItemClickListener {

    private val suggestionAdapter: SuggestionAdapter by lazy {
        SuggestionAdapter(emptyList(),this)
    }
    private lateinit var autoCompleteTextView: AutoCompleteTextView
    private lateinit var suggestionDao: SuggestionDao
    private lateinit var apiService: ApiService
    private lateinit var suggestionRecyclerView: RecyclerView
    private lateinit var viewModel: SearchViewModel
    private lateinit var searchRepository: SearchRepository
    private lateinit var binding :ActivitySearchableBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchableBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = SearchViewModel(searchRepository)

        suggestionDao = RoomDB.getInstance(applicationContext).suggestionDao()
        apiService = RetrofitClient.apiService

        searchRepository = SearchRepository(suggestionDao, apiService)
        autoCompleteTextView = binding.autoCompleteTextView
        suggestionRecyclerView = binding.suggestionRecyclerView

        suggestionRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        suggestionRecyclerView.adapter = suggestionAdapter

        insertSuggestion(SuggestionEntity(1,"Electronics"))
        insertSuggestion(SuggestionEntity(2, "Fashion"))
        insertSuggestion(SuggestionEntity(3,"Home Appliances"))
        insertSuggestion(SuggestionEntity(4,"Sports"))
        insertSuggestion(SuggestionEntity(5,"Kids"))
        insertSuggestion(SuggestionEntity(6,"Books"))
        insertSuggestion(SuggestionEntity(7,"Groceries"))

        autoCompleteTextView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString()
                if (query.isEmpty()){ suggestionRecyclerView.visibility= View.GONE }
                else{
                    suggestionRecyclerView.visibility= View.VISIBLE
                    suggestionAdapter.updateSuggestions(viewModel.fetchSuggestions(query))
                }
            }
            override fun afterTextChanged(s: Editable?) {}

        })
    }

    override fun performSearch(brandTitle: SuggestionEntity, view: View) {
        autoCompleteTextView.setText(brandTitle.suggestionText)
        suggestionRecyclerView.visibility=View.GONE
        val bundle = Bundle()
        bundle.putString("brandTitle", autoCompleteTextView.text.toString())
        autoCompleteTextView.setText("")
        Snackbar.make(view, "Showing Result for ${brandTitle.suggestionText} ", Snackbar.LENGTH_SHORT).show()
    }

    private fun insertSuggestion(suggestion: SuggestionEntity) {
        lifecycleScope.launch {
            suggestionDao.insertSuggestion(suggestion)
        }
    }

}