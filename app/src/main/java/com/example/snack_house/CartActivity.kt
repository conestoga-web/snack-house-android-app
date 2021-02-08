package com.example.snack_house

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.TextView

class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val btnCheckout = findViewById<View>(R.id.btnCheckout) as Button
        val btnShopping = findViewById<View>(R.id.btnShopping) as Button
        val mListView = findViewById<View>(R.id.listViewCart) as ListView
        val txtSubtotal = findViewById<View>(R.id.txtSubtotal) as TextView

        if (mListItems == null) {
            mListItems = ListViewAdapter()
        }
        mListView.adapter = mListItems

        val myCart = intent
        val cartItem: CartItem = myCart.getParcelableExtra("cartItem")
        val product = cartItem.product
        val index = product?.name?.let { checkProductIdentity(it) }

        if (index == -1) {
            mListItems!!.addItem(product.name, product.unitPrice, cartItem.quantity)
        } else {
            val cItem = index?.let { mListItems!!.getItem(it) }
            if (cItem != null) {
                cItem.quantity = cartItem.quantity
            }
        }
        mListItems!!.notifyDataSetChanged()

        val subTotal = subTotalCalulator()
        txtSubtotal.text = "Subtotal: $$subTotal"

        btnShopping.setOnClickListener {
            val url = "products://list_detail"
            val backToShopping = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(backToShopping)
        }

        btnCheckout.setOnClickListener { //CartItem.setCartJson(getApplicationContext(), cartItem);
            val myCart = Intent(this@CartActivity, CheckoutActivity::class.java)
            myCart.putExtra("myCheckout", subTotal)
            startActivity(myCart)
        }
    }

    fun subTotalCalulator(): Double {
        var sum = 0.0
        for (i in 0 until mListItems!!.count) {
            val item = mListItems!!.getItem(i)
            sum += item.price * item.quantity
        }

        sum = Math.round(sum * 100).toDouble()
        sum = sum / 100
        return sum
    }

    fun checkProductIdentity(productName: String): Int {
        var res = -1
        for (i in 0 until mListItems!!.count) {
            val item = mListItems!!.getItem(i)
            if (productName == item.name) {
                res = i
            }
        }
        return res
    }

    companion object {
        private var mListItems: ListViewAdapter? = null
    }
}