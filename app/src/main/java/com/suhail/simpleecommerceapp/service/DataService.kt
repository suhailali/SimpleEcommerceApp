package com.suhail.simpleecommerceapp.service

import com.suhail.simpleecommerceapp.data.OrderStatus
import com.suhail.simpleecommerceapp.data.Product
import com.suhail.simpleecommerceapp.data.Store

interface DataService {
    suspend fun getStoreDetails(): Result<Store>
    suspend fun getProducts(): Result<List<Product>>
    suspend fun postOrder(list: List<Product>): Result<OrderStatus>
}