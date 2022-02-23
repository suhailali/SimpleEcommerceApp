package com.suhail.simpleecommerceapp.order

import com.suhail.simpleecommerceapp.data.Product

interface OrderSummaryRepository {
    fun placeOrder(list: List<Product>)
}