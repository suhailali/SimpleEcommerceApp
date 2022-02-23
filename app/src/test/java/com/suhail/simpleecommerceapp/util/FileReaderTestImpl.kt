package com.suhail.simpleecommerceapp.util

import java.io.InputStreamReader

class FileReaderTestImpl: FileReader {
    override fun loadJSONFile(path: String): String {
        val reader = InputStreamReader(this.javaClass.classLoader!!.getResourceAsStream(path))
        val content = reader.readText()
        reader.close()
        return content
    }
}