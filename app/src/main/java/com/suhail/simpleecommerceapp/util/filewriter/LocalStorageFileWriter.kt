package com.suhail.simpleecommerceapp.util.filewriter

import android.content.Context
import android.util.Log
import java.io.BufferedWriter
import java.io.File
import java.io.IOException


class LocalStorageFileWriter(private val context: Context): FileWriter {

    override fun writeToFile(fileName: String, data: String) {
        val root = context.filesDir
        val outDir = File(root.absolutePath + File.separator + "OrderSummary.json")
        if (!outDir.isDirectory) {
            outDir.mkdir();
        }
        try {
            if (!outDir.isDirectory) {
                throw IOException(
                    "Unable to create directory OrderSummary.json. Maybe the SD card is mounted?"
                )
            }
            val outputFile = File(outDir, fileName)
            val writer = BufferedWriter(java.io.FileWriter(outputFile))
            writer.write(data)
            Log.w("write to File", "success")
            writer.close()
        } catch (e: IOException) {
            Log.w("write to File", e.message, e)
        }
    }
}