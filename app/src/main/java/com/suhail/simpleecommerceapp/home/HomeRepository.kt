package com.suhail.simpleecommerceapp.home

import com.suhail.simpleecommerceapp.data.Product
import com.suhail.simpleecommerceapp.data.Store

interface HomeRepository {
    suspend fun getStoreDetails(): Result<Store>
    suspend fun getProducts(): Result<List<Product>>
}