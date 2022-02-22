package com.suhail.simpleecommerceapp.util

import android.content.Context
import java.io.IOException

class FileReader(private val context: Context) {
    fun loadJSONFile(path: String): String? {
        return try {
            context.assets.open(path)
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            null
        }
    }
}