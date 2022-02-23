package com.suhail.simpleecommerceapp.util

import com.suhail.simpleecommerceapp.util.filewriter.FileWriter

class FileWriterTestImpl: FileWriter {
    override fun writeToFile(fileName: String, data: String): Boolean {
        return true
    }
}