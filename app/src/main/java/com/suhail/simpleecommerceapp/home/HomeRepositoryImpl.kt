package com.suhail.simpleecommerceapp.home

import com.suhail.simpleecommerceapp.data.Product
import com.suhail.simpleecommerceapp.data.Store
import com.suhail.simpleecommerceapp.service.DataService
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val dataService: DataService) : HomeRepository {

    override suspend fun getStoreDetails(): Result<Store> {
        return dataService.getStoreDetails()
    }

    override suspend fun getProducts(): Result<List<Product>> {
        return dataService.getProducts()
    }
}