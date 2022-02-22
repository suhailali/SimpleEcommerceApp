package com.suhail.simpleecommerceapp.home

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(viewModel: HomeViewModel, navController: NavHostController) {
    Text(text = "HomeScreen")
}