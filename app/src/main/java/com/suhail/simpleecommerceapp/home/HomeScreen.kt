package com.suhail.simpleecommerceapp.home

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.suhail.simpleecommerceapp.SCREEN_ORDER_SUMMARY
import com.suhail.simpleecommerceapp.data.Product
import com.suhail.simpleecommerceapp.data.Store
import com.suhail.simpleecommerceapp.ui.util.UiState

@Composable
fun HomeScreen(viewModel: HomeViewModel, navController: NavHostController) {

    val storeDetailState = viewModel.storeDetails
    val storeProductState = viewModel.products
    val scrollState = rememberScrollState()
    Box {
        Column(
            modifier = Modifier.scrollable(
                state = scrollState,
                orientation = Orientation.Vertical
            )
        ) {
            if (storeDetailState.value is UiState.Success) {
                val storeDetails = (storeDetailState.value as UiState.Success).value
                StoreInfo(store = storeDetails)
            }
            if (storeProductState.value is UiState.Success) {
                val productList = (storeProductState.value as UiState.Success).value
                Products(productList, viewModel = viewModel)
            }
        }
        OrderSummary {
            navController.navigate(SCREEN_ORDER_SUMMARY)
        }
    }
}

@Composable
fun StoreInfo(store: Store) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(color = MaterialTheme.colors.primary)
    ) {
//       Image(painter = painterResource(id = R.color.purple_200), contentDescription = "StoreImage")
        Text(text = store.name.orEmpty())
    }
}

@Composable
fun Products(productList: List<Product>, viewModel: HomeViewModel) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.secondary)
            .padding(bottom = 40.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(productList) { product ->
            var textState by remember { mutableStateOf(product.selected_quantity.toString()) }
            ProductRow(product = product, textState) {
                run {
                    val updatedQuantity = viewModel.updateQuantity(
                        product.selected_quantity.toString(),
                        it,
                        product.available_quantity
                    )
                    product.selected_quantity = updatedQuantity.toInt()
                    textState = updatedQuantity
                }
            }
        }
    }
}

@Composable
fun ProductRow(product: Product, quantityState: String, onClickQuantity: (Boolean) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
    ) {
        Text(text = product.id.toString())
        Text(text = product.name)
        QuantitySelection(quantityState, onClickQuantity)
    }
}

@Composable
fun QuantitySelection(textState: String, onClickQuantity: (Boolean) -> Unit) {
    Row {
        TextButton(onClick = {
            onClickQuantity(false)
        }) {
            Text(text = "-")
        }
        Text(text = textState)
        TextButton(onClick = {
            onClickQuantity(true)
        }) {
            Text(text = "+")
        }
    }
}

@Composable
fun BoxScope.OrderSummary(onClick: () -> Unit) {
    Box(
        Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .height(40.dp)
            .align(Alignment.BottomCenter),
    ) {
        Button(onClick = { onClick() }) {
            Text(text = "Order Summary")
        }
    }

}