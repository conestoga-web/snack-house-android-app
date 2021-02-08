package com.example.snack_house

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.util.*

class CartItemList {
    val cartList: ArrayList<CartItem>? = null
    private var mCount = 0
    val count: Int
        get() {
            mCount = cartList!!.size
            return mCount
        }

    fun findItem(id: String): CartItem? {
        for (c in cartList!!) {
            if (c.product!!.productID === id) return c
        }
        return null
    }

    fun addProduct(product: Product?, quantity: Int) {
        val c = CartItem(product, quantity)
        cartList!!.add(c)
    }

    fun addItem(element: CartItem) {
        cartList!!.add(element)
    }

    fun addIndexItem(index: Int, element: CartItem) {
        cartList!!.add(index, element)
    }

    fun getIndexItem(index: Int): CartItem {
        return cartList!![index]
    }

    fun setIndexItem(index: Int, element: CartItem) {
        cartList!![index] = element
    }

    fun removeAt(index: Int) {
        cartList!!.removeAt(index)
    }

    fun clear() {
        cartList!!.clear()
    }

    companion object {
        val instance = CartItemList()
        private var gson: Gson? = null

        fun getCartJson(context: Context): CartItemList {
            val sp = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
            val strCart = sp.getString("cartItem", "")
            val listType = object : TypeToken<CartItemList?>() {}.type

            return gson!!.fromJson(strCart, listType)
        }

        fun setCartJson(context: Context, element: CartItem) {
            val cart = CartItemList()
            cart.addItem(element)
            gson = GsonBuilder().create()

            val listType = object : TypeToken<CartItemList?>() {}.type
            val json = gson?.toJson(cart, listType)
            val sp = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
            val editor = sp.edit()

            editor.putString("cartItem", json)
            editor.commit()
        }
    }
}