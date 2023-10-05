package com.cloudsect.myapplication.ui.product_detail.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProductDetailResponse(

    @SerializedName("product_id")
    @Expose
    val productId: Int,

    @SerializedName("brand")
    @Expose
    val brand: ProductBrand,

    @SerializedName("images")
    @Expose
    val images: List<ProductImages>,

    @SerializedName("colors")
    @Expose
    val colors: List<ColorModel>,

    @SerializedName("sizes")
    @Expose
    val sizes: List<ProductSize>,

    @SerializedName("product_name")
    @Expose
    val productName: String,

    @SerializedName("discount_available")
    @Expose
    val discountAvailable: Boolean,

    @SerializedName("discount_price")
    @Expose
    val discountPrice: String,

    @SerializedName("discount_percent")
    @Expose
    val discountPercent: String,

    @SerializedName("product_price")
    @Expose
    val productPrice: String,

    @SerializedName("new_price")
    @Expose
    val newPrice: String,

    @SerializedName("meta_title")
    @Expose
    val metaTitle: String,

    @SerializedName("product_description")
    @Expose
    val productDescription: String,

    @SerializedName("product_available")
    @Expose
    val productAvailable: Boolean,

    @SerializedName("units_in_stock")
    @Expose
    val unitsInStock: Int,

    @SerializedName("meta_description")
    @Expose
    val metaDescription: String,

    @SerializedName("created_at")
    @Expose
    val createdAt: String,

    @SerializedName("category_name")
    @Expose
    val categoryName: String,

    @SerializedName("material")
    @Expose
    val material: String,

    @SerializedName("category")
    @Expose
    val category: Int,

    @SerializedName("feedback")
    @Expose
    val feedback: List<FeedBack>
):Serializable
