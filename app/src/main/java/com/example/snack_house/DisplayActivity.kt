package com.example.snack_house

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class DisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        val pID = intent.extras?.get("Position") as Int
        val product = Product.product?.get(pID)
        val name = findViewById<View>(R.id.txtProductName) as TextView
        if (product != null) {
            name.text = product.name
        }

        val description = findViewById<View>(R.id.txtDescription) as TextView
        if (product != null) {
            description.text = product.description
        }

        val photo = findViewById<View>(R.id.imgProduct) as ImageView
        if (product != null) {
            photo.setImageResource(product.imageResourceId)
        }
        if (product != null) {
            photo.contentDescription = product.name
        }

        val txtQuantity = findViewById<View>(R.id.editQty) as EditText
        val cartItem = CartItem()
        val btnCart = findViewById<View>(R.id.btnCart) as Button

        btnCart.setOnClickListener {
            val number = txtQuantity.text.toString() + ""
            if (number === "") {
                txtQuantity.setError("Enter a number")
            } else {
                if (product != null) {
                    cartItem.addEachProduct(product, number.toInt())
                }
                val myCartItem = Intent(this@DisplayActivity, CartActivity::class.java)
                myCartItem.putExtra("cartItem", cartItem)
                startActivity(myCartItem)
            }
        }
    }
}