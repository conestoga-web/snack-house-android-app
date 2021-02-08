package com.example.snack_house

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {
    private var mProduct: ArrayList<Product>? = null
    private var mCurrentProduct: Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mProduct = Product.product
        mCurrentProduct = (mProduct)!!.get(arguments!!.getInt("Position"))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_detail, container, false)
        val txtProductName = v.findViewById<View>(R.id.txtProductName) as TextView
        txtProductName.text = mCurrentProduct!!.name

        val description = v.findViewById<View>(R.id.txtDescription) as TextView
        description.text = mCurrentProduct!!.description

        val photo = v.findViewById<View>(R.id.imgProduct) as ImageView
        photo.setImageResource(mCurrentProduct!!.imageResourceId)
        photo.contentDescription = mCurrentProduct!!.name

        val txtQuantity = v.findViewById<View>(R.id.editQty) as EditText
        val cartItem = CartItem()
        val btnCart = v.findViewById<View>(R.id.btnCart) as Button

        btnCart.setOnClickListener {
            val number = txtQuantity.text.toString() + ""
            cartItem.addEachProduct(mCurrentProduct!!, number.toInt())

            val myCartItem = Intent(activity, CartActivity::class.java)
            myCartItem.putExtra("cartItem", cartItem)
            startActivity(myCartItem)
        }
        return v
    }

    companion object {
        @JvmStatic
        fun newInstance(position: Int): DetailFragment {
            val args = Bundle()
            args.putInt("Position", position)

            val fragment = DetailFragment()
            fragment.arguments = args
            return fragment
        }
    }
}