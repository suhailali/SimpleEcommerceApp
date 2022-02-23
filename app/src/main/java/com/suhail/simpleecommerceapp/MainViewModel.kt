package com.suhail.simpleecommerceapp

import androidx.lifecycle.ViewModel
import com.suhail.simpleecommerceapp.data.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {
    private var orderSummary:List<Product>? = null

    fun getOrderSummary(): List<Product>? {
        return orderSummary
    }

    fun setOrderSummary(list: List<Product>) {
        orderSummary = list
    }
}