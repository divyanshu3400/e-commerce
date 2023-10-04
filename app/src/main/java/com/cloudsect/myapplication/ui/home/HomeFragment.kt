package com.cloudsect.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.cloudsect.myapplication.R
import com.cloudsect.myapplication.databinding.FragmentHomeBinding
import com.cloudsect.myapplication.ui.categories.model.ProductResponse
import com.cloudsect.myapplication.ui.home.adapter.BrandLogoRVAdapter
import com.cloudsect.myapplication.ui.home.adapter.NewArrivalRVAdapter
import com.cloudsect.myapplication.ui.wishlist.model.WishlistProductModel
import com.cloudsect.myapplication.util.ImageSliderString
import com.cloudsect.myapplication.util.WindowsUtil

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel:HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getBrands().observe(viewLifecycleOwner) {brandResponse ->
            setBrandAdapter(brandResponse)
        }
        setImageSlider()
//        setNewArrivalAdapter()
    }

    private fun setBrandAdapter(brandResponse: List<BrandModel>) {

        binding.brandRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val adapter = context?.let { BrandLogoRVAdapter(it, brandResponse) }
        binding.brandRecyclerView.adapter = adapter
    }

    private fun setImageSlider() {
        val imageSlider =
            context?.let { ImageSliderString(it, binding.viewPager, binding.dotsLayout, true) }

        // Example image URLs
        val imageUrls = arrayOf(
            "https://nurserynisarga.in/wp-content/uploads/2021/06/ruby.jpg",
            "https://nurserynisarga.in/wp-content/uploads/2019/09/1800-1600-1.jpg",
            "https://5.imimg.com/data5/TR/AY/CJ/SELLER-91578059/rubber-plant-500x500.jpg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTldJHqebezI5SU3IMcbnczeCivbNoLZ16XBA&usqp=CAU",
            "https://www.inntinn.in/cdn/shop/products/rubber-plant-three-in-one-inntinn-in-1.jpg?v=1675507335"
        )
        imageSlider?.setImageUrls(imageUrls)
    }


    private fun setNewArrivalAdapter() {

        val column = if (context?.let { WindowsUtil.isTablet(it) } == true) {
            resources.getInteger(R.integer.columns_tablet)
        } else {
            resources.getInteger(R.integer.columns_mobile)
        }
        binding.recyclerView.layoutManager = GridLayoutManager(context, column)
//        val adapter = context?.let { NewArrivalRVAdapter(it, ) }
//        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}