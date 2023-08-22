package com.cloudsect.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager.widget.ViewPager
import com.cloudsect.myapplication.R
import com.cloudsect.myapplication.databinding.FragmentHomeBinding
import com.cloudsect.myapplication.ui.home.adapter.NewArrivalRVAdapter
import com.cloudsect.myapplication.ui.wishlist.model.WishlistProductModel
import com.cloudsect.myapplication.util.ImageSlider
import com.cloudsect.myapplication.util.ImageSliderString

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setImageSlider()
        setWishlistAdapter()
    }

    private fun setImageSlider() {
        val imageSlider = context?.let { ImageSliderString(it, binding.viewPager, binding.dotsLayout) }

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


    private fun setWishlistAdapter() {
        binding.recyclerView.layoutManager= GridLayoutManager(context,2)
        val adapter = context?.let { NewArrivalRVAdapter(it,getProductList()) }
        binding.recyclerView.adapter = adapter
    }

    private fun getProductList(): List<WishlistProductModel> {
        val obj = WishlistProductModel()
        obj.productPrice = "$90.00"
        obj.productTitle = "Hot Leather Jeans"
        obj.productDesc = "lorem ipsum"
        obj.productImageUrl =
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRDZQ3Y5pKzQoDZKzFbs34KFS1kV7tfM32mAA&usqp=CAU"

        val obj1 = WishlistProductModel()
        obj1.productPrice = "$90.00"
        obj1.productTitle = "Hot Leather Jeans"
        obj1.productDesc = "lorem ipsum"
        obj1.productImageUrl =
            "https://khushboonursery.in/cdn/shop/products/653726be-3ac8-4e97-94c9-d23cd0b3e3bf.png?v=1593257101"

        val obj2 = WishlistProductModel()
        obj2.productPrice = "$90.00"
        obj2.productTitle = "Hot Leather Jeans"
        obj2.productDesc = "lorem ipsum"
        obj2.productImageUrl =
            "https://www.inntinn.in/cdn/shop/products/rubber-plant-tall-inntinn-in-1.jpg?v=1675507382"

        val obj3 = WishlistProductModel()
        obj3.productPrice = "$90.00"
        obj3.productTitle = "Hot Leather Jeans"
        obj3.productDesc = "lorem ipsum"
        obj3.productImageUrl =
            "https://m.media-amazon.com/images/I/61CdNh5zOrL.jpg"

        return mutableListOf(
            obj, obj1, obj2,obj3
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}