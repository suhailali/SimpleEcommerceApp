package com.suhail.simpleecommerceapp.service

import com.suhail.simpleecommerceapp.data.OrderStatus
import com.suhail.simpleecommerceapp.data.Product
import com.suhail.simpleecommerceapp.data.Store
import com.suhail.simpleecommerceapp.util.filewriter.FileWriter
import com.suhail.simpleecommerceapp.util.jsonconverter.JsonDataHandler
import javax.inject.Inject

class MockServiceImpl @Inject constructor(private val jsonDataHandler: JsonDataHandler, private val fileWriter: FileWriter): DataService {
    override suspend fun getStoreDetails(): Result<Store> {
        val data = jsonDataHandler.getStoreDetails("data/StoreInfo.json")
        return if(data!=null) {
            Result.success(data)
        } else {
            Result.failure(Throwable("Data not found"))
        }
    }

    override suspend fun getProducts(): Result<List<Product>> {
        val data = jsonDataHandler.getProducts("data/Products.json")
        return if (data != null) {
            Result.success(data)
        } else {
            Result.failure(Throwable("Data not found"))
        }
    }

    override suspend fun postOrder(list: List<Product>): Result<OrderStatus> {
        val data = jsonDataHandler.getJsonForOrder(list)
        println("jaba" + data)
        val success = fileWriter.writeToFile("OrderSummary", data)
        return if(success) Result.success(OrderStatus(id=1, isSuccess = success))
        else Result.failure(Throwable("Failed to place order"))
    }
}