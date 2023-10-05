package com.cloudsect.myapplication.ui.product_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.cloudsect.myapplication.R
import com.cloudsect.myapplication.databinding.FragmentProductDetailsBinding
import com.cloudsect.myapplication.ui.categories.model.ProductResponse
import com.cloudsect.myapplication.ui.product_detail.adapter.ColorAdapter
import com.cloudsect.myapplication.ui.product_detail.adapter.SizeAdapter
import com.cloudsect.myapplication.ui.product_detail.model.ProductDetailResponse
import com.cloudsect.myapplication.ui.product_detail.model.ProductImages
import com.cloudsect.myapplication.ui.wishlist.model.WishlistProductModel
import com.cloudsect.myapplication.util.Colors.Companion.getColorList
import com.cloudsect.myapplication.util.ImageSliderString
import com.google.android.material.snackbar.Snackbar

class ProductDetailsFragment : Fragment(), SizeAdapter.OnItemListener, ColorAdapter.OnItemListener {
    private lateinit var binding: FragmentProductDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.fragment_product_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val productDetail = arguments?.getSerializable("product") as ProductDetailResponse

        setImageSlider(productDetail.images)

        binding.modal = setProductDetail()

        binding.sizeRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val sizeAdapter = context?.let { SizeAdapter(it, getSizeList(), this) }
        binding.sizeRecyclerView.adapter = sizeAdapter

        binding.colorRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        val coloAdapter = context?.let { ColorAdapter(it, getColorList(),this) }

        binding.colorRecyclerView.adapter=coloAdapter
    }

    private fun setImageSlider(images: List<ProductImages>) {
        val imageSlider =
            context?.let { ImageSliderString(it, binding.viewPager, binding.dotsLayout,false) }
        val imageUrls = arrayOf(
            "https://nurserynisarga.in/wp-content/uploads/2021/06/ruby.jpg",
            "https://nurserynisarga.in/wp-content/uploads/2019/09/1800-1600-1.jpg",
            "https://5.imimg.com/data5/TR/AY/CJ/SELLER-91578059/rubber-plant-500x500.jpg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTldJHqebezI5SU3IMcbnczeCivbNoLZ16XBA&usqp=CAU",
            "https://www.inntinn.in/cdn/shop/products/rubber-plant-three-in-one-inntinn-in-1.jpg?v=1675507335"
        )
        imageSlider?.setImageUrls(imageUrls)
    }

    private fun setProductDetail(): WishlistProductModel {
        val modal = WishlistProductModel()
        modal.productTitle = "Brand New Headphone"
        modal.productSecTitle = "Boat"
        modal.rating = 5.0f
        modal.totalReviews = 445
        modal.desc =
            "The life of a foot for a shoe is from the heel to the longest toe. Shoe size is Associate " +
                    "in the Nursing alphabetical indication of the fitting size of a shoe for someone"
        return modal
    }

    private fun getSizeList(): List<String> {
        return mutableListOf(
            "S","M","L" ,"XL", "XXL",
        )
    }


    override fun setProductAccordingToSize(sizeName: String, adapterPos: Int, view: View) {
        Snackbar
            .make(
                view, "Clicked $sizeName",
                Snackbar.LENGTH_SHORT
            )
            .show()
    }

    override fun setProductAccordingToColor(color:String, adapterPos: Int, view: View) {
        Snackbar
            .make(
                view, "Clicked $color",
                Snackbar.LENGTH_SHORT
            )
            .show()
    }

}