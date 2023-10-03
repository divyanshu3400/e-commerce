package com.cloudsect.myapplication.util

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.app.Dialog
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.AutoCompleteTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cloudsect.myapplication.R
import com.cloudsect.myapplication.retrofit.RetrofitClient
import com.cloudsect.myapplication.retrofit.ApiService
import com.cloudsect.myapplication.search.RoomDB
import com.cloudsect.myapplication.search.SearchRepository
import com.cloudsect.myapplication.search.SearchViewModel
import com.cloudsect.myapplication.search.SuggestionDao

class SearchDialogFragment(private val context: Context) : DialogFragment() {
    private lateinit var autoCompleteTextView: AutoCompleteTextView
    private lateinit var suggestionRecyclerView: RecyclerView
    private lateinit var viewModel: SearchViewModel
    private lateinit var apiService: ApiService
    private lateinit var suggestionDao: SuggestionDao


    private lateinit var searchRepository: SearchRepository




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.search_layout_dialog, container, false)

        autoCompleteTextView = view.findViewById(R.id.autoCompleteTextView)
        suggestionRecyclerView = view.findViewById(R.id.suggestionRecyclerView)


        suggestionDao = RoomDB.getInstance(context).suggestionDao()
        apiService = RetrofitClient.apiService

        searchRepository = SearchRepository(suggestionDao, apiService)
        viewModel = SearchViewModel(searchRepository)

        suggestionRecyclerView.layoutManager = LinearLayoutManager(context)
//        suggestionRecyclerView.adapter = suggestionAdapter


        autoCompleteTextView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString()
                if (query.equals("")){
//                    suggestionAdapter.clearAdapterList()
                }
                else{
//                    suggestionAdapter.updateSuggestions(viewModel.fetchSuggestions(query))
                }
            }

            override fun afterTextChanged(s: Editable?) {}

        })

        return view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        val layoutParams = WindowManager.LayoutParams()
        layoutParams.copyFrom(dialog.window?.attributes)
        layoutParams.gravity = Gravity.TOP
        dialog.window?.attributes = layoutParams
//        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return dialog
    }
}

