package com.suhail.simpleecommerceapp.util.filewriter

interface FileWriter {
    fun writeToFile(fileName: String, data: String): Boolean
}