package com.suhail.simpleecommerceapp.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suhail.simpleecommerceapp.data.Product
import com.suhail.simpleecommerceapp.data.Store
import com.suhail.simpleecommerceapp.ui.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {
    var storeDetails = mutableStateOf<UiState<Store>>(UiState.Empty)
        private set
    var products = mutableStateOf<UiState<List<Product>>>(UiState.Empty)
        private set

    fun fetchData() {
        if (storeDetails.value is UiState.Empty) {
            getStoreDetails()
        }
        if (products.value is UiState.Empty) {
            getProducts()
        }
    }

    fun getStoreDetails() {
        viewModelScope.launch {
            storeDetails.value = UiState.Loading
            repository.getStoreDetails().let {
                if (it.isSuccess) {
                    storeDetails.value = UiState.Success(it.getOrDefault(Store("")))
                } else {
                    storeDetails.value = UiState.Error("Failed to access store")
                }
            }
        }
    }

    fun getProducts() {
        viewModelScope.launch {
            products.value = UiState.Loading
            repository.getProducts().let {
                if (it.isSuccess) {
                    products.value = UiState.Success(it.getOrDefault(listOf()))
                } else {
                    products.value = UiState.Error("Failed to access products")
                }
            }
        }
    }

    fun updateQuantity(currentValue: Int, isIncrement: Boolean, availableQuantity: Int): Int {
        var currentQuantity = currentValue
        if (isIncrement) {
            if (currentQuantity < availableQuantity)
                currentQuantity += 1
        } else {
            if (currentQuantity > 0)
                currentQuantity -= 1
        }
        return currentQuantity
    }

    fun getOrderItems(): ArrayList<Product> {
        val list = ArrayList<Product>()
        if (products.value is UiState.Success) {
            (products.value as UiState.Success).value.forEach {
                if (it.selected_quantity > 0) {
                    list.add(it)
                }
            }
        }
        return list
    }
}