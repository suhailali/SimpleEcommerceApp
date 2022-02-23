package com.suhail.simpleecommerceapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    var id: Int,
    var name: String,
    var price: Int,
    var discounted_price: Int,
    var discount_percent: Int,
    var image: String,
    var description: String,
    var available_quantity: Int,
    var selected_quantity: Int = 0
) : Parcelable