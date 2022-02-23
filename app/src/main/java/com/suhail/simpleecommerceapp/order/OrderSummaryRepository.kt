package com.suhail.simpleecommerceapp.order

import com.suhail.simpleecommerceapp.data.OrderStatus
import com.suhail.simpleecommerceapp.data.Product

interface OrderSummaryRepository {
    suspend fun placeOrder(list: List<Product>): Result<OrderStatus>
}