package com.cloudsect.myapplication.ui.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.cloudsect.myapplication.databinding.FragmentCategoriesBinding
import com.cloudsect.myapplication.ui.categories.adapter.CategoriesBrandAdapter
import com.cloudsect.myapplication.ui.categories.adapter.ChildCategoriesBrandAdapter
import com.cloudsect.myapplication.ui.home.BrandModel
import com.google.android.material.snackbar.Snackbar

class CategoriesFragment : Fragment(), CategoriesBrandAdapter.OnItemListener ,ChildCategoriesBrandAdapter.OnItemListener{
    private lateinit var viewModel: CategoriesViewModel
    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater,container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBrandAdapter()
    }

    private fun setBrandAdapter() {
        binding.parentRV.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL,false)
        val adapter = context?.let { CategoriesBrandAdapter(it,getBrandLogoList(),this) }
        binding.parentRV.adapter=adapter
    }
    private fun getBrandLogoList(): List<BrandModel> {
        val brandModel = BrandModel()
        brandModel.brandImage ="https://thumbs.dreamstime.com/b/handsome-man-black-suit-white-shirt-posing-studio-attractive-guy-fashion-hairstyle-confident-man-short-beard-125019349.jpg"
        brandModel.brandId =1
        brandModel.brandTitle="Men's"

        val brandModel1 = BrandModel()
        brandModel1.brandId =2
        brandModel1.brandImage =
            "https://i.pinimg.com/550x/5b/72/42/5b72428637220bda71af81b3e2cd7a08.jpg"
        brandModel1.brandTitle="Kids"

        val brandModel2 = BrandModel()
        brandModel2.brandId =3
        brandModel2.brandImage =
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQy8WfCuahrqYhCm2r9gIi2c1hBxBuABgKEfVPLH2QQYT3nJorWu-4bt73BVJdy02wtfW0&usqp=CAU"
        brandModel2.brandTitle="Women's"

        val brandModel3 = BrandModel()
        brandModel3.brandId =4
        brandModel3.brandTitle="Sports"
        brandModel3.brandImage =
            "https://media.npr.org/assets/img/2020/06/10/gettyimages-200199027-001_wide-3ff0f063a2bf1ab01550d3508c816bc43009d215-s1400-c100.jpg"

        val brandModel4 = BrandModel()
        brandModel4.brandId =5
        brandModel4.brandTitle="Appliances"
        brandModel4.brandImage =
            "https://media.istockphoto.com/id/1174598609/photo/set-of-contemporary-house-appliances-isolated-on-white.jpg?s=612x612&w=0&k=20&c=bBMILbCpLkhIxbL7sAAXaFOaFaSXFCt80ccHgl7iiWM="

        val brandModel5 = BrandModel()
        brandModel5.brandId =6
        brandModel5.brandTitle="Appliances"
        brandModel5.brandImage =
            "https://media.istockphoto.com/id/1174598609/photo/set-of-contemporary-house-appliances-isolated-on-white.jpg?s=612x612&w=0&k=20&c=bBMILbCpLkhIxbL7sAAXaFOaFaSXFCt80ccHgl7iiWM="


        return mutableListOf(
            brandModel, brandModel1, brandModel2, brandModel3,brandModel4
        )
    }

    override fun setChildCategoryAdapter(adapterPos: Int,view: View) {
        updateChildRV(adapterPos,view)
    }

    private fun updateChildRV(adapterPos: Int, view: View) {
        binding.childRV.layoutManager = GridLayoutManager(context,3)
        val adapter = context?.let { ChildCategoriesBrandAdapter(it,getBrandLogoList(),this) }
        binding.childRV.adapter=adapter

    }

    override fun getProduct(brandTitle: String, brandId: Int, view: View) {
        Snackbar
            .make(
                view, "Clicked: $brandTitle",
                Snackbar.LENGTH_SHORT
            )
            .show()
    }

}