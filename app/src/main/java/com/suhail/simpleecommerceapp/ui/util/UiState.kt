package com.suhail.simpleecommerceapp.ui.util

sealed class UiState<out T : Any> {
    data class Success<out T: Any>(val value: T) : UiState<T>()
    data class Error(val msg: String) : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    object Empty : UiState<Nothing>()
}