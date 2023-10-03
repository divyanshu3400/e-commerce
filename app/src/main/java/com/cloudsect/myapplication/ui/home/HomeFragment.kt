package com.cloudsect.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.cloudsect.myapplication.R
import com.cloudsect.myapplication.databinding.FragmentHomeBinding
import com.cloudsect.myapplication.ui.home.adapter.BrandLogoRVAdapter
import com.cloudsect.myapplication.ui.home.adapter.NewArrivalRVAdapter
import com.cloudsect.myapplication.ui.wishlist.model.WishlistProductModel
import com.cloudsect.myapplication.util.ImageSliderString
import com.cloudsect.myapplication.util.WindowsUtil

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setImageSlider()
        setNewArrivalAdapter()
        setBrandAdapter(view)
    }

    private fun setBrandAdapter(view: View) {

        binding.brandRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val adapter = context?.let { BrandLogoRVAdapter(it, getBrandLogoList(view)) }
        binding.brandRecyclerView.adapter = adapter
    }

    private fun getBrandLogoList(view: View): List<BrandModel> {
        val brandModel = BrandModel()
        brandModel.brandImage =
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR3k9Cx3GPOnHJUlvZCJKvPUVLe6ONGqvIgBw&usqp=CAU"
        brandModel.brandId = 1

        val brandModel1 = BrandModel()
        brandModel.brandId = 2
        brandModel1.brandImage =
            "https://www.irreverentgent.com/wp-content/uploads/2020/12/Gucci-Logo-min.png"

        val brandModel2 = BrandModel()
        brandModel2.brandId = 3
        brandModel2.brandImage =
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRXqe0j8P6_YwyKHdrVAJfdi6bwZNnXsOVOTwFiwAIS1cIELOaFjMpfXlQi__tfgboob0A&usqp=CAU"
        val brandModel3 = BrandModel()
        brandModel3.brandId = 4
        brandModel3.brandImage =
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSFRwTT1QZ_nwZo1BYfJ6Ms8Ybx2eEp69jcQMGX-Ve7aEWLPI8xqPO2Yhc2klZj6DN2Yz8&usqp=CAU"

        return mutableListOf(
            brandModel, brandModel1, brandModel2, brandModel3
        )

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
        val adapter = context?.let { NewArrivalRVAdapter(it, getProductList()) }
        binding.recyclerView.adapter = adapter
    }

    private fun getProductList(): List<WishlistProductModel> {
        val obj = WishlistProductModel()
        obj.productPrice = "$40.00"
        obj.productTitle = "Women's Cotton T-shirt"
        obj.productDesc = "Raymond"
        obj.productImageUrl =
            "https://www.beyoung.in/api/cache/catalog/products/images_for_customized_products_26_4_2022/custom_t-shirt_women_base_26_4_2022_700x933.jpg"

        val obj1 = WishlistProductModel()
        obj1.productPrice = "$50.00"
        obj1.productTitle = "Laptop Bag"
        obj1.productDesc = "Nike"
        obj1.productImageUrl =
            "https://5.imimg.com/data5/SELLER/Default/2022/1/WZ/DB/XY/20579664/dell-premier-slim-laptop-backpack-15-pe1520ps-with-water-resistant-exterior-and-eva-foam-cushioning.jpg"

        val obj2 = WishlistProductModel()
        obj2.productPrice = "$100.00"
        obj2.productTitle = "Boat Headphone"
        obj2.productDesc = "Boat"
        obj2.productImageUrl =
            "https://m.media-amazon.com/images/I/614q47uoPNL._AC_UF1000,1000_QL80_.jpg"

        val obj3 = WishlistProductModel()
        obj3.productPrice = "$90.00"
        obj3.productTitle = "Adidas Shoes"
        obj3.productDesc = "Adidas"
        obj3.productImageUrl =
            "https://media.istockphoto.com/id/956501428/photo/sport-shoes-on-isolated-white-background.jpg?s=612x612&w=0&k=20&c=BdklqnfGUvf02-2CxYsw-AnrbE3e-B5zhE9JQILEEW4="

        return mutableListOf(obj, obj1, obj2, obj3)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}