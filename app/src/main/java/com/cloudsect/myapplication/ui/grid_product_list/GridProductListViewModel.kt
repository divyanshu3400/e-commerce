package com.cloudsect.myapplication.ui.grid_product_list

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cloudsect.myapplication.ProductRepository
import com.cloudsect.myapplication.models.MessageResponse
import com.cloudsect.myapplication.ui.product_detail.model.ProductDetailReq
import com.cloudsect.myapplication.ui.product_detail.model.ProductDetailResponse

class GridProductListViewModel(application: Application) : AndroidViewModel(application) {
    private var productRepository: ProductRepository = ProductRepository()

    fun addWishlist(context: Context, productId: Int): LiveData<MessageResponse> {
        return productRepository.addToWishList(context,productId)
    }
    fun removeWishlist(context: Context,productId: Int): LiveData<MessageResponse> {
        return productRepository.removeFromWishlist(context,productId)
    }

    fun productDetail(productDetailReq: ProductDetailReq): LiveData<ProductDetailResponse> {
        return productRepository.productDetail(productDetailReq)
    }

}