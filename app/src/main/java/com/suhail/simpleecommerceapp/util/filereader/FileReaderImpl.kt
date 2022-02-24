package com.suhail.simpleecommerceapp.util.filereader

import android.content.Context
import java.io.IOException

class FileReaderImpl(private val context: Context) : FileReader {
    override fun loadJSONFile(path: String): String? {
        return try {
            context.assets.open(path)
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            null
        }
    }
}