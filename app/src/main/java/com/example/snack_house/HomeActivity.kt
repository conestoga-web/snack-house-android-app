package com.example.snack_house

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.util.Linkify
import android.view.View
import android.widget.TextView
import java.util.regex.Pattern

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val productLink = findViewById<View>(R.id.productLink) as TextView
        val text = "See our menu"
        productLink.text = text

        val mTransform = Linkify.TransformFilter { match, url -> "" }
        val pattern = Pattern.compile("menu")
        Linkify.addLinks(productLink, pattern, "products://list_detail", null, mTransform)
    }
}
