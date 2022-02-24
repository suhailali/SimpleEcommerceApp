package com.suhail.simpleecommerceapp.util.filereader

interface FileReader {
    fun loadJSONFile(path: String): String?
}