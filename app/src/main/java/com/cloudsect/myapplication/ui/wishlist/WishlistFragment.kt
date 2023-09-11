package com.cloudsect.myapplication.ui.wishlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cloudsect.myapplication.R
import com.cloudsect.myapplication.databinding.FragmentWishlistBinding
import com.cloudsect.myapplication.ui.wishlist.adapter.ProductRVAdapter
import com.cloudsect.myapplication.ui.wishlist.model.WishlistProductModel

class WishlistFragment : Fragment() {

    private lateinit var  binding : FragmentWishlistBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.fragment_wishlist, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWishlistAdapter()
    }
    private fun setWishlistAdapter() {
        binding.recyclerView.layoutManager=LinearLayoutManager(context)
        val adapter = context?.let { ProductRVAdapter(it,getProductList()) }
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