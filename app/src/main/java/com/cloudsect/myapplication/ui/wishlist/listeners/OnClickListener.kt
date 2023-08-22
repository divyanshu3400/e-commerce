package com.cloudsect.myapplication.ui.wishlist.listeners

import com.cloudsect.myapplication.ui.wishlist.model.WishlistProductModel

interface OnClickListener {
    fun getWishlistData(wishlistProductModel: WishlistProductModel)
}