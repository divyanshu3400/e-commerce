package com.cloudsect.myapplication.ui.grid_product_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.cloudsect.myapplication.R
import com.cloudsect.myapplication.databinding.FragmentGridProductListBinding
import com.cloudsect.myapplication.ui.home.adapter.NewArrivalRVAdapter
import com.cloudsect.myapplication.ui.wishlist.model.WishlistProductModel

class GridProductListFragment : Fragment() {
    private var _binding :FragmentGridProductListBinding?=null
    private val binding get() = _binding!!

    private lateinit var viewModel: GridProductListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGridProductListBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val brandTitle = arguments?.getString("brandTitle")
        binding.setCategoryName(brandTitle)
        setProductGridAdapter()
    }

    private fun setProductGridAdapter() {
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

        val obj4 = WishlistProductModel()
        obj4.productPrice = "$90.00"
        obj4.productTitle = "Hot Leather Jeans"
        obj4.productDesc = "lorem ipsum"
        obj4.productImageUrl =
            "https://www.inntinn.in/cdn/shop/products/rubber-plant-tall-inntinn-in-1.jpg?v=1675507382"

        val obj5 = WishlistProductModel()
        obj5.productPrice = "$90.00"
        obj5.productTitle = "Hot Leather Jeans"
        obj5.productDesc = "lorem ipsum"
        obj5.productImageUrl =
            "https://m.media-amazon.com/images/I/61CdNh5zOrL.jpg"

        val obj6 = WishlistProductModel()
        obj6.productPrice = "$90.00"
        obj6.productTitle = "Hot Leather Jeans"
        obj6.productDesc = "lorem ipsum"
        obj6.productImageUrl =
            "https://www.inntinn.in/cdn/shop/products/rubber-plant-tall-inntinn-in-1.jpg?v=1675507382"

        val obj7 = WishlistProductModel()
        obj7.productPrice = "$90.00"
        obj7.productTitle = "Hot Leather Jeans"
        obj7.productDesc = "lorem ipsum"
        obj7.productImageUrl =
            "https://m.media-amazon.com/images/I/61CdNh5zOrL.jpg"

        return mutableListOf(
            obj, obj1, obj2,obj3,obj4,obj5,obj6,obj7
        )
    }


}