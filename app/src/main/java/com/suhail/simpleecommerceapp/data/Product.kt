package com.suhail.simpleecommerceapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    var id: Int = 0,
    var name: String? = null,
    var price: Int = 0,
    var discounted_price: Int = 0,
    var discount_percent: Int = 0,
    var image: String? = null,
    var description: String? = null,
    var available_quantity: Int = 0,
    var selected_quantity: Int = 0
) : Parcelable