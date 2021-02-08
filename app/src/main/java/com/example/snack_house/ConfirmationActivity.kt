package com.example.snack_house

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView

class ConfirmationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        val txtConfirm = findViewById<View>(R.id.txtConfirm) as TextView
        val firstName = CheckoutActivity.mSharePref.getString("firstName", "")
        val lastName = CheckoutActivity.mSharePref.getString("lastName", "")
        val email = CheckoutActivity.mSharePref.getString("email", "")
        val phone = CheckoutActivity.mSharePref.getString("phone", "")
        val amount = CheckoutActivity.mSharePref.getString("amount", "")
        val payment = CheckoutActivity.mSharePref.getString("payment", "")

        txtConfirm.text = """
            Thank you. Your order information is as follows. 
            
            """.trimIndent()
        txtConfirm.append("""
    --------------------------------
    
    """.trimIndent())
        txtConfirm.append("First Name: $firstName\n")
        txtConfirm.append("Last Name: $lastName\n")
        txtConfirm.append("Email: $email\n")
        txtConfirm.append("Phone: $phone\n")
        txtConfirm.append("Amount: $$amount\n")
        txtConfirm.append("Payment: $payment")
    }
}