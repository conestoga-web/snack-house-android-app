package com.example.snack_house

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.snack_house.DetailFragment.Companion.newInstance

/**
 * A simple [Fragment] subclass.
 */
class ListFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val listAdapter = ArrayAdapter(activity, android.R.layout.simple_list_item_1, Product.product)
        val v = inflater.inflate(R.layout.fragment_list, container, false)
        val listProducts = v.findViewById<View>(R.id.listProducts) as ListView
        listProducts.adapter = listAdapter

        val itemClickListener = OnItemClickListener { listProducts, itemView, position, id ->
            if (ProductActivity.landscapeMode == true) {
                val fm = fragmentManager
                val detailFrag: Fragment = newInstance(position)
                fm!!.beginTransaction()
                        .replace(R.id.detailHolder, detailFrag)
                        .commit()
            } else {
                val intent = Intent(activity, DisplayActivity::class.java)
                intent.putExtra("Position", id.toInt())
                startActivity(intent)
            }
        }
        listProducts.onItemClickListener = itemClickListener
        return v
    }
}