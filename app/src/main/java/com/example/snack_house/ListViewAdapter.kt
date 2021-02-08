package com.example.snack_house

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.util.*

class ListViewAdapter : BaseAdapter() {
    val listViewItemList = ArrayList<ListViewItem>()

    override fun getCount(): Int {
        return listViewItemList.size
    }

    override fun getItem(position: Int): ListViewItem {
        return listViewItemList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val pos = position
        val context = parent.context

        if (convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.listview_item, parent, false)
        }

        val textName = convertView?.findViewById<View>(R.id.textName) as TextView
        val textPrice = convertView?.findViewById<View>(R.id.textPrice) as TextView
        val textQuantity = convertView?.findViewById<View>(R.id.textQuantity) as TextView
        val listViewItem = listViewItemList[position]

        textName.text = listViewItem.name
        textPrice.text = "$" + listViewItem.price
        textQuantity.text = "Qty: " + listViewItem.quantity
        return convertView!!
    }

    fun addItem(name: String?, price: Double?, quantity: Int) {
        val item = ListViewItem()
        item.name = name
        item.price = price!!
        item.quantity = quantity
        listViewItemList.add(item)
    }
}