package com.suhail.simpleecommerceapp.home

import com.suhail.simpleecommerceapp.data.Product
import com.suhail.simpleecommerceapp.data.Store

class HomeRepositoryImpl : HomeRepository {

    override suspend fun getStoreDetails(): Result<Store> {
        return Result.success(Store("ABCD"))
    }

    override suspend fun getProducts(): Result<List<Product>> {
        TODO("Not yet implemented")
    }
}