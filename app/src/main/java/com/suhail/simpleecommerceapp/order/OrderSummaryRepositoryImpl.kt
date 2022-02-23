package com.suhail.simpleecommerceapp.order

import com.suhail.simpleecommerceapp.data.Product
import com.suhail.simpleecommerceapp.util.filewriter.FileWriter

class OrderSummaryRepositoryImpl(private val fileWriter: FileWriter): OrderSummaryRepository {

    override fun placeOrder(list: List<Product>) {
        fileWriter.writeToFile("one","Abcd")
    }

}