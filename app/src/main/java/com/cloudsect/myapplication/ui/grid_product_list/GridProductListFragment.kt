package com.cloudsect.myapplication.ui.grid_product_list

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.cloudsect.myapplication.R
import com.cloudsect.myapplication.databinding.FragmentGridProductListBinding
import com.cloudsect.myapplication.ui.categories.model.ProductResponse
import com.cloudsect.myapplication.ui.home.adapter.NewArrivalRVAdapter
import com.cloudsect.myapplication.ui.product_detail.model.ProductDetailReq

class GridProductListFragment : Fragment(),NewArrivalRVAdapter.OnWishlistCheckChangedListener,NewArrivalRVAdapter.OnParentClickListener {
    private var _binding :FragmentGridProductListBinding?=null
    private val binding get() = _binding!!
    private lateinit var viewModel: GridProductListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGridProductListBinding.inflate(inflater,container,false)

        viewModel = ViewModelProvider(this)[GridProductListViewModel::class.java]
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
        val adapter = context?.let { NewArrivalRVAdapter(it,productResponseList,this,this) }
        binding.recyclerView.adapter = adapter
    }

    override fun onChange(isChanged: Boolean, context: Context, productId: Int) {
        binding.llLoader.visibility = View.VISIBLE
        if (isChanged){
            viewModel.addWishlist(context,productId).observe(viewLifecycleOwner){messageResponse ->
                Toast.makeText(context, messageResponse.message,Toast.LENGTH_SHORT).show()
                binding.llLoader.visibility = View.GONE
            }
        }
        else
        {
            viewModel.removeWishlist(context,productId).observe(viewLifecycleOwner){messageResponse ->
                Toast.makeText(context, messageResponse.message,Toast.LENGTH_SHORT).show()
                binding.llLoader.visibility = View.GONE
            }
        }
    }

    override fun onClick(productResponse: ProductResponse) {
        val request = ProductDetailReq(productResponse.product_id,productResponse.category_name)
        viewModel.productDetail(request).observe(viewLifecycleOwner){productDetailResponse ->
            val bundle = Bundle()
            bundle.putSerializable("product", productDetailResponse)
            val navOptions = NavOptions.Builder()
                .setEnterAnim(android.R.animator.fade_in)
                .setExitAnim(android.R.animator.fade_out)
                .setPopEnterAnim(android.R.animator.fade_in)
                .setPopExitAnim(android.R.animator.fade_out)
                .build()

            Navigation.findNavController(requireView()).navigate(
                R.id.productDetailsFragment,
                bundle,
                navOptions
            )
        }
    }

}