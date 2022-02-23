package com.suhail.simpleecommerceapp.data

data class Product(
    var id: Int,
    var name:String,
    var price: Int,
    var discounted_price:Int,
    var discount_percent:Int,
    var image: String,
    var description: String,
    var available_quantity: Int,
    var selected_quantity: Int = 0
)