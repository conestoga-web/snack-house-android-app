package com.example.snack_house

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class Product : Parcelable {
    var productID: String? = null
    var name: String? = null
    var description: String? = null
    var imageResourceId = 0
    var unitPrice = 0.0

    constructor() {
        product = ArrayList<Product>()
        var tempEntry = Product("T001", "Egg Muffin", "Our signature breakfast sandwich boasts one freshly-cracked Canada Grade A large egg topped with Canadian bacon and a slice of tasty processed cheddar cheese, sitting happily on a toasted English muffin.", R.drawable.egg_muffin, 7.99)
        product!!.add(tempEntry)
        tempEntry = Product("T002", "Bacon Egg Muffin", "Take your bacon and eggs the tasty. Crisp bacon on a freshly-cracked Canada Grade A egg. Topped with processed cheddar cheese,nestled comfortably in a toasty Engilsh muffin.", R.drawable.bacon_egg_muffin, 9.49)
        product!!.add(tempEntry)
        tempEntry = Product("T003", "Sausage Muffin", "Wake up to delicious flavour with a big bite savoury sausage and a slice of gloden processed chedda cheese held inside a toasted English muffin.", R.drawable.sausage_muffin, 8.99)
        product!!.add(tempEntry)
        tempEntry = Product("T004", "Bacon & Hash Brown Wrap", "Kick off your morning with lots of scrambled eggs made fresh on our grill, crisp bacon, a golden hashbrown, topped with a slice of processed cheddar cheese, all wrapped up in a soft tortilla.", R.drawable.bacon_egg_hashbrown, 7.49)
        product!!.add(tempEntry)
        tempEntry = Product("T005", "Double_Filet_O_Fish", "Bite into not one. but two crisp and flaky white fish filets. Topped with processed cheddar cheese and tangy tartar sauce, it's double the delicious on a warm steamed bun.", R.drawable.double_filet_o_fish, 9.49)
        product!!.add(tempEntry)
        tempEntry = Product("T006", "Sausage & Hash Brown Wrap", "Take on the morning with scrambled eggs made fresh in our kitchens, our savoury breakfast sausage, a crispy golden hash brown, topped with a slice of tasty processed cheddar cheese.", R.drawable.sausage_hashbrown, 9.99)
        product!!.add(tempEntry)
        tempEntry = Product("T006", "Filet-O-Fish", "Dive right in. Our Filet-O-Fish is pure temptation on a lightly steamed bun. topped with processed cheddar cheese and tangy tartar sauce", R.drawable.filet_o_fish, 6.99)
        product!!.add(tempEntry)
    }

    constructor(productID: String?, name: String?, description: String?, imageID: Int, unitPrice: Double) {
        this.productID = productID
        this.name = name
        this.description = description
        this.imageResourceId = imageID
        this.unitPrice = unitPrice
    }

    constructor(parcel: Parcel) {
        productID = parcel.readString()
        name = parcel.readString()
        description = parcel.readString()
        imageResourceId = parcel.readInt()
        unitPrice = parcel.readDouble()
    }

    override fun toString(): String {
        return name!!
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(productID)
        dest.writeString(name)
        dest.writeString(description)
        dest.writeInt(imageResourceId)
        dest.writeDouble(unitPrice)
    }

    companion object {
        val instanceProduct = Product()
        var product: ArrayList<Product>? = null
            private set

        @JvmField
        val CREATOR: Parcelable.Creator<Product> = object : Parcelable.Creator<Product> {
            override fun createFromParcel(parcel: Parcel): Product? {
                return Product(parcel)
            }

            override fun newArray(size: Int): Array<Product?> {
                return arrayOfNulls(size)
            }
        }
    }
}