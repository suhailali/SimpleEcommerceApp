package com.suhail.simpleecommerceapp.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suhail.simpleecommerceapp.data.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderSummaryViewModel @Inject constructor(private val orderSummaryRepository: OrderSummaryRepository): ViewModel() {

    fun placeOrder(list: List<Product>) {
        viewModelScope.launch {
            orderSummaryRepository.placeOrder(list)
        }
    }
}