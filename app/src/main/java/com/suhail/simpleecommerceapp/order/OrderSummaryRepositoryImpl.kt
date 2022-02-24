package com.suhail.simpleecommerceapp.order

import com.suhail.simpleecommerceapp.data.OrderStatus
import com.suhail.simpleecommerceapp.data.Product
import com.suhail.simpleecommerceapp.service.DataService
import kotlinx.coroutines.delay

class OrderSummaryRepositoryImpl(private val dataService: DataService) : OrderSummaryRepository {

    override suspend fun placeOrder(list: List<Product>): Result<OrderStatus> {
        delay(2000)
        return dataService.postOrder(list)
    }

}