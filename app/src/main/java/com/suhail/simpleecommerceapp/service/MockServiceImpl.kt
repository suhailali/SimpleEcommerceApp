package com.suhail.simpleecommerceapp.service

import com.suhail.simpleecommerceapp.data.OrderStatus
import com.suhail.simpleecommerceapp.data.Product
import com.suhail.simpleecommerceapp.data.Store
import com.suhail.simpleecommerceapp.util.JsonDataProvider
import javax.inject.Inject

class MockServiceImpl @Inject constructor(private val jsonDataProvider: JsonDataProvider): DataService {
    override suspend fun getStoreDetails(): Result<Store> {
        val data = jsonDataProvider.getStoreDetails("data/StoreInfo.json")
        return if(data!=null) {
            Result.success(data)
        } else {
            Result.failure(Throwable("Data not found"))
        }
    }

    override suspend fun getProducts(): Result<List<Product>> {
        TODO("Not yet implemented")
    }

    override suspend fun postOrder(): Result<OrderStatus> {
        TODO("Not yet implemented")
    }
}