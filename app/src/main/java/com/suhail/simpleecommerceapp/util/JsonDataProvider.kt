package com.suhail.simpleecommerceapp.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.suhail.simpleecommerceapp.data.Store

class JsonDataProvider(private val fileReader: FileReader) {

    fun getStoreDetails(path: String): Store? {
        val data = fileReader.loadJSONFile(path) ?: return null
        val value = object : TypeToken<Store>() {}.type
        return Gson().fromJson(data, value)
    }
}