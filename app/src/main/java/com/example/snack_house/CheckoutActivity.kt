package com.example.snack_house

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup

class CheckoutActivity : AppCompatActivity() {
    var editFirstName: EditText? = null
    var editLastName: EditText? = null
    var editEmail: EditText? = null
    var editPhone: EditText? = null
    var editAmount: EditText? = null
    var select: RadioButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        editFirstName = findViewById<View>(R.id.editFirstName) as EditText
        editLastName = findViewById<View>(R.id.editLastName) as EditText
        editEmail = findViewById<View>(R.id.editEmail) as EditText
        editPhone = findViewById<View>(R.id.editPhone) as EditText
        editAmount = findViewById<View>(R.id.editAmount) as EditText

        val btnSubmit = findViewById<View>(R.id.btnSubmit) as Button
        val btnBackToShop = findViewById<View>(R.id.btnBackToShop) as Button
        val radioPayment = findViewById<View>(R.id.radioPayment) as RadioGroup
        val checkout = intent
        val amount = checkout.getDoubleExtra("myCheckout", 0.0)

        editAmount!!.setText(amount.toString())
        mSharePref = getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE)
        mEditor = mSharePref.edit()

        btnBackToShop.setOnClickListener {
            val url = "products://list_detail"
            val backToShopping = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(backToShopping)
        }

        btnSubmit.setOnClickListener {
            var error = ""
            val firstName = editFirstName!!.text.toString()
            if (firstName.isEmpty()) {
                editFirstName!!.hint = "Please input your first name!"
                error = "No input for first name"
            }
            val lastName = editLastName!!.text.toString()
            if (lastName.isEmpty()) {
                editLastName!!.hint = "Please input your last name!"
                error = "No input for last name"
            }
            val email = editEmail!!.text.toString()
            if (email.isEmpty()) {
                editEmail!!.hint = "Please input your email address!"
                error = "No input for email address"
            }
            val phoneNum = editPhone!!.text.toString()
            if (phoneNum.isEmpty()) {
                editPhone!!.hint = "Please input your phone number!"
                error = "No input for phone number"
            }
            if (select == null) {
                error = "No input for payment method"
            }
            if (error === "") {
                saveCustomerData()
                val myCheckout = Intent(this@CheckoutActivity, ConfirmationActivity::class.java)
                startActivity(myCheckout)
            }
        }
        radioPayment.setOnCheckedChangeListener { group, checkedId -> select = findViewById<View>(checkedId) as RadioButton }
    }

    fun saveCustomerData() {
        mEditor!!.putString("firstName", editFirstName!!.text.toString())
        mEditor!!.putString("lastName", editLastName!!.text.toString())
        mEditor!!.putString("email", editEmail!!.text.toString())
        mEditor!!.putString("phone", editPhone!!.text.toString())
        mEditor!!.putString("amount", editAmount!!.text.toString())
        mEditor!!.putString("payment", select!!.text.toString())
        mEditor!!.apply()
    }

    companion object {
        private const val SHARE_NAME = "CUSTOMER_ORDER_INFO"
        lateinit var mSharePref: SharedPreferences
        var mEditor: Editor? = null
    }
}