package com.cloudsect.myapplication.ui.categories

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.cloudsect.myapplication.databinding.FragmentCategoriesBinding
import com.cloudsect.myapplication.ui.categories.adapter.CategoriesBrandAdapter
import com.cloudsect.myapplication.ui.categories.adapter.ChildCategoriesBrandAdapter
import com.cloudsect.myapplication.ui.categories.model.CategoryResponse
import com.cloudsect.myapplication.ui.categories.model.CategoryViewModel
import com.cloudsect.myapplication.ui.categories.model.ChildCatReq
import com.cloudsect.myapplication.ui.categories.model.ChildCatRes
import com.cloudsect.myapplication.util.WindowsUtil
import com.google.android.material.snackbar.Snackbar

class CategoriesFragment : Fragment(),
    CategoriesBrandAdapter.OnItemListener, ChildCategoriesBrandAdapter.OnItemListener {
    private var _binding: FragmentCategoriesBinding? = null
    private lateinit var viewModel: CategoryViewModel
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getNavItems()
            .observe(viewLifecycleOwner) { categoryResponse ->
                setBrandAdapter(categoryResponse)
            }
    }

    private fun setBrandAdapter(categoryResponse: List<CategoryResponse>?) {
        if (categoryResponse != null) {
            if (categoryResponse.isNotEmpty()) {
                val column = if (context?.let { WindowsUtil.isTablet(it) } == true) { 2 }
                else { 1 }
                Log.d("TAG", "categoryResponse: $categoryResponse")
                binding.parentRV.layoutManager = GridLayoutManager(
                    context, column,
                    LinearLayoutManager.VERTICAL, false
                )
                val adapter = context?.let { CategoriesBrandAdapter(it, categoryResponse, this) }
                binding.parentRV.adapter = adapter
                binding.progressHorizontal?.visibility=View.GONE

                setChildCategoryAdapter(categoryResponse[0].nav_id)
            }
        }
    }

    override fun setChildCategoryAdapter(id: Int) {
        val childCatReq = ChildCatReq(id)
        viewModel.getNavChildItems(childCatReq)
            .observe(viewLifecycleOwner) { childCategoryResponse ->
                setChildBrandAdapter(childCategoryResponse)
            }
    }


    private fun setChildBrandAdapter(childCategoryResponse: List<ChildCatRes>?) {
        if (childCategoryResponse != null) {
            if (childCategoryResponse.isNotEmpty()) {
                val column = if (context?.let { WindowsUtil.isTablet(it) } == true) { 5 }
                else { 3 }

                binding.childRV.layoutManager = GridLayoutManager(context, column)
                val adapter =
                    context?.let { ChildCategoriesBrandAdapter(it, childCategoryResponse, this) }
                binding.childRV.adapter = adapter
                binding.progressHorizontal?.visibility=View.GONE
            }
        }


    }

    override fun getProduct(brandId: Int) {
        Snackbar
            .make(
                requireView(), "Clicked: $brandId",
                Snackbar.LENGTH_SHORT
            )
            .show()
    }

}