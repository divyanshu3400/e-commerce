package com.cloudsect.myapplication.ui.wishlist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.cloudsect.myapplication.R
import com.cloudsect.myapplication.databinding.ActivityWishlistBinding
import com.cloudsect.myapplication.ui.wishlist.adapter.ProductRVAdapter
import com.cloudsect.myapplication.ui.wishlist.model.WishlistProductModel

class WishlistActivity : AppCompatActivity() {

    private lateinit var  binding : ActivityWishlistBinding
    val context:Context =this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_wishlist)
        setContentView(binding.root)

        setWishlistAdapter()
    }

    private fun setWishlistAdapter() {
        binding.recyclerView.layoutManager=LinearLayoutManager(context)
        val adapter = ProductRVAdapter(context,getProductList())
        binding.recyclerView.adapter = adapter
    }

    private fun getProductList(): List<WishlistProductModel> {
        val obj = WishlistProductModel()
        obj.productPrice = "$90.00"
        obj.productTitle = "Hot Leather Jeans"
        obj.productDesc = "lorem ipsum"
        obj.productImageUrl =
            "https://images.pexels.com/photos/90946/pexels-photo-90946.jpeg?cs=srgb&dl=pexels-math-90946.jpg&fm=jpg"

        val obj1 = WishlistProductModel()
        obj1.productPrice = "$90.00"
        obj1.productTitle = "Hot Leather Jeans"
        obj1.productDesc = "lorem ipsum"
        obj1.productImageUrl =
            "https://images.pexels.com/photos/90946/pexels-photo-90946.jpeg?cs=srgb&dl=pexels-math-90946.jpg&fm=jpg"

        val obj2 = WishlistProductModel()
        obj2.productPrice = "$90.00"
        obj2.productTitle = "Hot Leather Jeans"
        obj2.productDesc = "lorem ipsum"
        obj2.productImageUrl =
            "https://images.pexels.com/photos/90946/pexels-photo-90946.jpeg?cs=srgb&dl=pexels-math-90946.jpg&fm=jpg"

        return mutableListOf(
            obj, obj1, obj2
        )
    }
}