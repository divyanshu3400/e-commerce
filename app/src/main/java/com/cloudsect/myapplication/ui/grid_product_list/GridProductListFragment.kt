package com.cloudsect.myapplication.ui.grid_product_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.cloudsect.myapplication.databinding.FragmentGridProductListBinding
import com.cloudsect.myapplication.ui.categories.model.ProductResponse
import com.cloudsect.myapplication.ui.home.adapter.NewArrivalRVAdapter

class GridProductListFragment : Fragment() {
    private var _binding :FragmentGridProductListBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGridProductListBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val productResponseList = arguments?.getSerializable("productResponseList") as ArrayList<ProductResponse>?
        binding.heading=arguments?.getString("heading")
        if (productResponseList != null) {
            setProductGridAdapter(productResponseList)
        }
        else{ Toast.makeText(context,"No Products Found", Toast.LENGTH_SHORT).show()}
    }

    private fun setProductGridAdapter(productResponseList: ArrayList<ProductResponse>) {
        binding.recyclerView.layoutManager= GridLayoutManager(context,2)
        val adapter = context?.let { NewArrivalRVAdapter(it,productResponseList) }
        binding.recyclerView.adapter = adapter
    }

}