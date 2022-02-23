package com.suhail.simpleecommerceapp.util.jsonconverter

import com.suhail.simpleecommerceapp.data.Product
import com.suhail.simpleecommerceapp.data.Store

interface DataHandler {
    fun getStoreDetails(path: String): Store?
    fun getProducts(path: String): List<Product>?
    fun getJsonForOrder(list: List<Product>): String
}