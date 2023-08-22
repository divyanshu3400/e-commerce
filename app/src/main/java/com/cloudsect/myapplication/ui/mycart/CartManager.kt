package com.cloudsect.myapplication.ui.mycart

import com.cloudsect.myapplication.util.CartItem

class CartManager {
    private val cartItems = mutableListOf<CartItem>()

    fun addItemToCart(product: Product) {
        val existingItem = cartItems.find { it.productId == product.productId }
        if (existingItem != null) {
            existingItem.quantity++
        } else {
            cartItems.add(CartItem(product.productId, product.productName, 1))
        }
    }

    fun removeItemFromCart(productId: String) {
        val itemToRemove = cartItems.find { it.productId == productId }
        if (itemToRemove != null) {
            if (itemToRemove.quantity > 1) {
                itemToRemove.quantity--
            } else {
                cartItems.remove(itemToRemove)
            }
        }
    }
}
