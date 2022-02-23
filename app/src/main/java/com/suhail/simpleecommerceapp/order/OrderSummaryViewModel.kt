package com.suhail.simpleecommerceapp.order

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suhail.simpleecommerceapp.data.OrderStatus
import com.suhail.simpleecommerceapp.data.Product
import com.suhail.simpleecommerceapp.ui.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderSummaryViewModel @Inject constructor(private val orderSummaryRepository: OrderSummaryRepository) :
    ViewModel() {

    var orderStatus = mutableStateOf<UiState<OrderStatus>>(UiState.Empty)
        private set

    fun placeOrder(list: List<Product>) {
        orderStatus.value = UiState.Loading
        viewModelScope.launch {
            val result = orderSummaryRepository.placeOrder(list)
            if (result.isSuccess) {
                orderStatus.value =
                    UiState.Success(result.getOrDefault(OrderStatus(isSuccess = true)))
            } else {
                orderStatus.value =
                    UiState.Error("Placing order failed. Please try again after sometime")
            }
        }
    }
}