package com.cloudsect.myapplication.ui.myorders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.cloudsect.myapplication.R
import com.cloudsect.myapplication.databinding.FragmentMyOrdersBinding
import com.cloudsect.myapplication.ui.myorders.model.MyOrderModal

class MyOrdersFragment : Fragment() {

    private lateinit var binding: FragmentMyOrdersBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_orders, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.myOrdersRV.layoutManager = LinearLayoutManager(context)
        val adapter = context?.let { MyOrdersAdapter(it, getOrdersList()) }
        binding.myOrdersRV.adapter = adapter
    }

    private fun getOrdersList(): List<MyOrderModal> {
        val obj = MyOrderModal()
        obj.productPrice = "$40.00"
        obj.productTitle = "Women's Cotton T-shirt"
        obj.productSecTitle = "Raymond"
        obj.productSizes= "M"
        obj.productImageUrl ="https://www.beyoung.in/api/cache/catalog/products/images_for_customized_products_26_4_2022/custom_t-shirt_women_base_26_4_2022_700x933.jpg"

        val obj1 = MyOrderModal()
        obj1.productPrice = "$50.00"
        obj1.productTitle = "Laptop Bag"
        obj1.productDesc = "Nike"
        obj1.productImageUrl ="https://5.imimg.com/data5/SELLER/Default/2022/1/WZ/DB/XY/20579664/dell-premier-slim-laptop-backpack-15-pe1520ps-with-water-resistant-exterior-and-eva-foam-cushioning.jpg"

        val obj2 = MyOrderModal()
        obj2.productPrice = "$100.00"
        obj2.productTitle = "Boat Headphone"
        obj2.productDesc = "Boat"
        obj2.productSizes= "6"
        obj2.productImageUrl ="https://m.media-amazon.com/images/I/614q47uoPNL._AC_UF1000,1000_QL80_.jpg"

        val obj3 = MyOrderModal()
        obj3.productPrice = "$90.00"
        obj3.productTitle = "Adidas Shoes"
        obj3.productDesc = "Adidas"
        obj3.productSizes= "6"
        obj3.productImageUrl ="https://media.istockphoto.com/id/956501428/photo/sport-shoes-on-isolated-white-background.jpg?s=612x612&w=0&k=20&c=BdklqnfGUvf02-2CxYsw-AnrbE3e-B5zhE9JQILEEW4="

        return mutableListOf(obj3, obj2, obj1, obj)

    }
}