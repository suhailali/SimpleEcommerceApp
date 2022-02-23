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
    var image: String? = "https://images.pexels.com/photos/7796734/pexels-photo-7796734.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
    var description: String? = null,
    var available_quantity: Int = 0,
    var selected_quantity: Int = 0
) : Parcelable