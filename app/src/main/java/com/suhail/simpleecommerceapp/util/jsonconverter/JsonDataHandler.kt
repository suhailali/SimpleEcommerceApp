package com.suhail.simpleecommerceapp.util.jsonconverter

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.suhail.simpleecommerceapp.data.Product
import com.suhail.simpleecommerceapp.data.Store
import com.suhail.simpleecommerceapp.util.filereader.FileReader


class JsonDataHandler(private val fileReader: FileReader): DataHandler {

    override fun getStoreDetails(path: String): Store? {
        val data = fileReader.loadJSONFile(path) ?: return null
        val value = object : TypeToken<Store>() {}.type
        return Gson().fromJson(data, value)
    }

    override fun getProducts(path: String): List<Product>? {
        val data = fileReader.loadJSONFile(path) ?: return listOf()
        val value = object : TypeToken<List<Product>>() {}.type
        return Gson().fromJson(data, value)
    }

    override fun getJsonForOrder(list: List<Product>): String {
        val gson = GsonBuilder().setPrettyPrinting().create()
        return gson.toJson(list)
    }
}