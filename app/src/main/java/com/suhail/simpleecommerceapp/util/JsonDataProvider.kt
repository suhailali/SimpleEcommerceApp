package com.suhail.simpleecommerceapp.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.suhail.simpleecommerceapp.data.Product
import com.suhail.simpleecommerceapp.data.Store

class JsonDataProvider(private val fileReader: FileReader) {

    fun getStoreDetails(path: String): Store? {
        val data = fileReader.loadJSONFile(path) ?: return null
        val value = object : TypeToken<Store>() {}.type
        return Gson().fromJson(data, value)
    }

    fun getProducts(path: String): List<Product>? {
        val data = fileReader.loadJSONFile(path) ?: return listOf()
        val value = object : TypeToken<List<Product>>() {}.type
        return Gson().fromJson(data, value)
    }
}