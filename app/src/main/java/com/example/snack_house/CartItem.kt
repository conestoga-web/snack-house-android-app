package com.example.snack_house

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class CartItem : Parcelable {
    var product: Product? = null
    var quantity = 0

    constructor() {}
    constructor(product: Product?, quantity: Int) {
        this.product = product
        this.quantity = quantity
    }

    constructor(parcel: Parcel) {
        product = parcel.readParcelable(Product::class.java.classLoader)
        quantity = parcel.readInt()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeParcelable(product, flags)
        dest.writeInt(quantity)
    }

    override fun describeContents(): Int {
        return 0
    }

    fun addQuantity(quantity: Int) {
        this.quantity += quantity
    }

    fun addEachProduct(product: Product, quantity: Int) {
        this.product = product
        this.quantity = quantity
    }

    companion object {
        private var mGson: Gson? = null
        @JvmField
        val CREATOR: Parcelable.Creator<CartItem> = object : Parcelable.Creator<CartItem> {
            override fun createFromParcel(parcel: Parcel): CartItem = CartItem(parcel)
            override fun newArray(size: Int): Array<CartItem?> = arrayOfNulls(size)
        }

        fun getCartJson(context: Context): CartItem {
            val sp = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
            val strCart = sp.getString("cartItem", "")
            val listType = object : TypeToken<CartItem?>() {}.type

            return mGson!!.fromJson(strCart, listType)
        }

        fun setCartJson(context: Context, cart: CartItem?) {
            mGson = GsonBuilder().create()
            val listType = object : TypeToken<CartItem?>() {}.type
            val json = mGson?.toJson(cart, listType)
            val sp = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
            val editor = sp.edit()

            editor.putString("cartItem", json)
            editor.commit()
        }
    }
}