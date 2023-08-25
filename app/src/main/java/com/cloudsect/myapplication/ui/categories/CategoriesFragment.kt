package com.cloudsect.myapplication.ui.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.cloudsect.myapplication.databinding.FragmentCategoriesBinding
import com.cloudsect.myapplication.ui.categories.adapter.CategoriesBrandAdapter
import com.cloudsect.myapplication.ui.categories.adapter.ChildCategoriesBrandAdapter
import com.cloudsect.myapplication.util.CategoryLists.Companion.getChildCategory
import com.cloudsect.myapplication.util.CategoryLists.Companion.getParentCategory
import com.google.android.material.snackbar.Snackbar

class CategoriesFragment : Fragment(),
    CategoriesBrandAdapter.OnItemListener ,ChildCategoriesBrandAdapter.OnItemListener{
    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater,container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBrandAdapter()
        setChildCategoryAdapter(1,view)
    }

    private fun setBrandAdapter() {
        binding.parentRV.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL,false)
        val adapter = context?.let { CategoriesBrandAdapter(it,getParentCategory(),this) }
        binding.parentRV.adapter=adapter
    }

    override fun setChildCategoryAdapter(adapterPos: Int,view: View) {
        updateChildRV(adapterPos,view)
    }

    private fun updateChildRV(adapterPos: Int, view: View) {
        binding.childRV.layoutManager = GridLayoutManager(context,3)
        val adapter = context?.let { ChildCategoriesBrandAdapter(it, getChildCategory(),this) }
        binding.childRV.adapter=adapter

    }

    override fun getProduct(brandTitle: String, brandId: Int, view: View) {
        Snackbar
            .make(
                view, "Clicked: $brandTitle",
                Snackbar.LENGTH_SHORT
            )
            .show()
    }

}