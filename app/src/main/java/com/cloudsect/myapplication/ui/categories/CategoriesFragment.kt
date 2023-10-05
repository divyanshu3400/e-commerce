package com.cloudsect.myapplication.ui.categories

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.cloudsect.myapplication.R
import com.cloudsect.myapplication.databinding.FragmentCategoriesBinding
import com.cloudsect.myapplication.retrofit.RetrofitClient
import com.cloudsect.myapplication.ui.categories.adapter.CategoriesBrandAdapter
import com.cloudsect.myapplication.ui.categories.adapter.ChildCategoriesBrandAdapter
import com.cloudsect.myapplication.ui.categories.model.CategoryResponse
import com.cloudsect.myapplication.ui.categories.model.CategoryViewModel
import com.cloudsect.myapplication.ui.categories.model.ChildCatReq
import com.cloudsect.myapplication.ui.categories.model.ChildCatRes
import com.cloudsect.myapplication.ui.categories.model.ProductResponse
import com.cloudsect.myapplication.util.WindowsUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
                val column = if (context?.let { WindowsUtil.isTablet(it) } == true) { 2 } else { 1 }
                binding.parentRV.layoutManager = GridLayoutManager(
                    context, column,
                    LinearLayoutManager.VERTICAL, false
                )
                val adapter = context?.let { CategoriesBrandAdapter(it, categoryResponse, this) }
                binding.parentRV.adapter = adapter
                setChildCategoryAdapter(categoryResponse[0].nav_id)
                binding.progressHorizontal?.visibility = View.GONE

            }
        }
    }

    override fun setChildCategoryAdapter(id: Int) {
        binding.llLoader?.visibility = View.VISIBLE
        val childCatReq = ChildCatReq(id)
        viewModel.getNavChildItems(childCatReq)
            .observe(viewLifecycleOwner) { childCategoryResponse ->
                setChildBrandAdapter(childCategoryResponse)
                binding.llLoader?.visibility =View.GONE
            }
    }


    private fun setChildBrandAdapter(childCategoryResponse: List<ChildCatRes>?) {
        if (childCategoryResponse != null) {
            if (childCategoryResponse.isNotEmpty()) {
                val column = if (context?.let { WindowsUtil.isTablet(it) } == true) { 5 } else { 3 }
                binding.childRV.layoutManager = GridLayoutManager(context, column)
                val adapter =
                    context?.let { ChildCategoriesBrandAdapter(it, childCategoryResponse, this) }
                binding.childRV.adapter = adapter
            }
        }


    }

    private fun getParentProduct(childCatRes: ChildCatRes) {
        binding.llLoader?.visibility =  View.VISIBLE
        val call: Call<List<ProductResponse>> =
            RetrofitClient.apiService.getParentProducts(childCatRes.pcat_id)
        call.enqueue(object : Callback<List<ProductResponse>?> {
            override fun onResponse(
                call: Call<List<ProductResponse>?>,
                response: Response<List<ProductResponse>?>
            ) {
                binding.llLoader?.visibility =  View.GONE
                val bundle = Bundle()
                bundle.putString("heading",childCatRes.pcat_name)
                if (response.isSuccessful) {
                    if (response.body()!!.isNotEmpty()){
                        bundle.putSerializable("productResponseList",
                            response.body()?.let { ArrayList(it) })
                        Navigation.findNavController(requireView())
                            .navigate(R.id.gridProductListFragment, bundle)
                    }
                    else{
                        bundle.putSerializable("productResponseList", null)
                        Navigation.findNavController(requireView())
                            .navigate(R.id.gridProductListFragment, bundle)
                    }
                }
            }

            override fun onFailure(call: Call<List<ProductResponse>?>, t: Throwable) {
                Log.d("TAG", "onResponse: $t")
                binding.llLoader?.visibility =  View.GONE
            }
        })
    }

    override fun getProduct(childCatRes: ChildCatRes) {
        getParentProduct(childCatRes)
    }

}