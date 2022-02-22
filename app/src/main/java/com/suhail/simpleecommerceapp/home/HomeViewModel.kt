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

    init {
        getStoreDetails()
    }

    fun getStoreDetails() {
        viewModelScope.launch {
            storeDetails.value = UiState.Loading
            repository.getStoreDetails().let {
                if(it.isSuccess) {
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
                if(it.isSuccess) {
                    products.value = UiState.Success(it.getOrDefault(listOf()))
                } else {
                    products.value = UiState.Error("Failed to access products")
                }
            }
        }
    }
}