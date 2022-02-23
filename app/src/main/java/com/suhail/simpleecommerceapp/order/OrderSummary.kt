package com.suhail.simpleecommerceapp.order

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavHostController
import com.suhail.simpleecommerceapp.MainViewModel
import com.suhail.simpleecommerceapp.SCREEN_ORDER_SUCCESS
import com.suhail.simpleecommerceapp.data.Product

@Composable
fun OrderSummary(viewModel: OrderSummaryViewModel, mainViewModel: MainViewModel, navController: NavHostController) {
    Column {
        OrderSummaryList(mainViewModel.getOrderSummary())
        Column(modifier = Modifier.weight(20f)) {
            AddressBox()
            BottomBar {
                viewModel.placeOrder(mainViewModel.getOrderSummary().orEmpty())
                navController.navigate(SCREEN_ORDER_SUCCESS)
            }
        }
    }
}

@Composable
fun ColumnScope.OrderSummaryList(orderSummary: List<Product>?) {
    LazyColumn(modifier = Modifier.weight(80f)) {
        items(orderSummary ?: listOf()) { product ->
            OrderItem(product)
        }
    }
}

@Composable
fun OrderItem(product: Product) {
    Column {
        Text(text = product.id.toString())
        Text(text = product.name)
    }
}

@Composable
fun ColumnScope.AddressBox() {
    val textState = remember { mutableStateOf(TextFieldValue()) }
    Box(modifier = Modifier.weight(2f)) {
        TextField(
            value = textState.value,
            onValueChange = { textState.value = it }
        )
    }
}

@Composable
fun ColumnScope.BottomBar(onClick: () -> Unit) {
    Box(modifier = Modifier.weight(1f)) {
        Text(text = "Total Price")
        Button(onClick = { onClick() }) {
            Text(text = "PlaceOrder")
        }
    }
}