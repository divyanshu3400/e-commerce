package com.cloudsect.myapplication.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cloudsect.myapplication.R
import com.cloudsect.myapplication.adapter.SuggestionAdapter
import com.cloudsect.myapplication.databinding.ActivityDashboardBinding
import com.cloudsect.myapplication.retrofit.RetrofitClient
import com.cloudsect.myapplication.retrofit.ApiService
import com.cloudsect.myapplication.search.RoomDB
import com.cloudsect.myapplication.search.SearchRepository
import com.cloudsect.myapplication.search.SearchViewModel
import com.cloudsect.myapplication.search.SuggestionDao
import com.cloudsect.myapplication.search.SuggestionEntity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
class DashboardActivity : AppCompatActivity(),SuggestionAdapter.OnItemClickListener {

    private lateinit var suggestionDao: SuggestionDao
    private lateinit var apiService: ApiService
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var autoCompleteTextView: AutoCompleteTextView
    private lateinit var suggestionRecyclerView: RecyclerView
    private lateinit var viewModel: SearchViewModel
    private lateinit var searchRepository: SearchRepository
    private lateinit var navController: NavController

    private val suggestionAdapter: SuggestionAdapter by lazy {
        SuggestionAdapter(emptyList(),this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
//        binding.appbar.visibility = View.GONE


        val navView: BottomNavigationView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_activity_dashboard)
        setBadges(navView)
//        supportActionBar?.hide()

        navView.setupWithNavController(navController)

        suggestionDao = RoomDB.getInstance(applicationContext).suggestionDao()
        apiService = RetrofitClient.apiService

        searchRepository = SearchRepository(suggestionDao, apiService)
        viewModel = SearchViewModel(searchRepository)

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
                if (query.isEmpty()){ suggestionRecyclerView.visibility= View.GONE
                    binding.backgroundLl.visibility = View.GONE}
                else{
                    suggestionRecyclerView.visibility= View.VISIBLE
                    binding.backgroundLl.visibility = View.VISIBLE
                    suggestionAdapter.updateSuggestions(viewModel.fetchSuggestions(query))
                }
            }
            override fun afterTextChanged(s: Editable?) {}

        })
    }

    private fun setBadges(navView: BottomNavigationView) {
        val cartBadge = navView.getOrCreateBadge(R.id.navigation_cart)
        val notifBadge = navView.getOrCreateBadge(R.id.navigation_notifications)
        notifBadge.isVisible = true
        cartBadge.isVisible = true
        cartBadge.number = 3
        notifBadge.number = 10
    }
    private fun insertSuggestion(suggestion: SuggestionEntity) {
        lifecycleScope.launch {
            suggestionDao.insertSuggestion(suggestion)
        }
    }
    override fun performSearch(brandTitle: SuggestionEntity, view: View) {
        autoCompleteTextView.setText(brandTitle.suggestionText)
        suggestionRecyclerView.visibility=View.GONE
        binding.backgroundLl.visibility = View.GONE
        val bundle = Bundle()
        bundle.putString("brandTitle", autoCompleteTextView.text.toString())
        autoCompleteTextView.setText("")

        navController.navigate(R.id.gridProductListFragment, bundle)
        Snackbar.make(view, "Showing Result for ${brandTitle.suggestionText} ", Snackbar.LENGTH_SHORT).show()
    }
}