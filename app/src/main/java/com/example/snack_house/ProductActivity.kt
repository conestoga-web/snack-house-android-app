package com.example.snack_house

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.snack_house.DetailFragment.Companion.newInstance

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val fm = supportFragmentManager
        var listFrag = fm.findFragmentById(R.id.listHolder)

        if (listFrag == null) {
            listFrag = ListFragment()
            fm.beginTransaction()
                    .add(R.id.listHolder, listFrag)
                    .commit()
        }

        mLandscapeMode = if (findViewById<View?>(R.id.detailHolder) != null) {
            val detailFrag: Fragment = newInstance(0)
            fm.beginTransaction()
                    .replace(R.id.detailHolder, detailFrag)
                    .commit()
            true
        } else {
            false
        }
    }

    companion object {
        protected var mLandscapeMode: Boolean? = null
        val landscapeMode: Boolean
            get() = mLandscapeMode!!
    }
}