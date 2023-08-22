package com.cloudsect.myapplication.ui.mycart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.cloudsect.myapplication.R
import com.cloudsect.myapplication.databinding.FragmentHomeBinding
import com.cloudsect.myapplication.databinding.FragmentMyCartBinding
import com.cloudsect.myapplication.ui.mycart.listener.Listener
import com.cloudsect.myapplication.ui.wishlist.adapter.CartProductRVAdapter
import com.cloudsect.myapplication.ui.wishlist.adapter.ProductRVAdapter
import com.cloudsect.myapplication.ui.wishlist.model.WishlistProductModel

class MyCartFragment : Fragment(),Listener  {

    private var _binding: FragmentMyCartBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyCartBinding.inflate(LayoutInflater.from(context),container,false)

        setWishlistAdapter()

        return binding.root
    }

    override fun increaseQuantity() {
        TODO("Not yet implemented")
    }

    override fun decreaseQuantity() {
        TODO("Not yet implemented")
    }

    private fun setWishlistAdapter() {
        binding.recyclerView.layoutManager= LinearLayoutManager(context)
        val adapter = context?.let { CartProductRVAdapter(it,getProductList()) }
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