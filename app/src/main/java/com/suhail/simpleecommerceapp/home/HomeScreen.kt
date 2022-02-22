package com.suhail.simpleecommerceapp.home

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.suhail.simpleecommerceapp.ui.util.UiState

@Composable
fun HomeScreen(viewModel: HomeViewModel, navController: NavHostController) {

    val storeDetailState = viewModel.storeDetails
    if (storeDetailState.value is UiState.Success) {
        val storeDetails = (storeDetailState.value as UiState.Success).value
        Text(text = storeDetails.name.orEmpty())
    }
}