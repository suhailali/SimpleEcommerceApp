package com.suhail.simpleecommerceapp.order

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.suhail.simpleecommerceapp.MainViewModel
import com.suhail.simpleecommerceapp.R
import com.suhail.simpleecommerceapp.SCREEN_ORDER_SUCCESS
import com.suhail.simpleecommerceapp.data.Product
import com.suhail.simpleecommerceapp.ui.util.UiState
import kotlinx.coroutines.launch

@Composable
fun OrderSummary(
    viewModel: OrderSummaryViewModel,
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    val orderStatus = viewModel.orderStatus
    val orderSummaryList = mainViewModel.getOrderSummary().orEmpty()
    val addressState = viewModel.address
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    Box {
        Column {
            OrderSummaryList(orderSummaryList)
            Column(modifier = Modifier.weight(20f)) {
                AddressBox(addressState) {
                    viewModel.address = it
                }
                BottomBar(totalPrice = viewModel.getTotalPrice(orderSummaryList)) {
                    if (viewModel.address.isNotEmpty()) {
                        viewModel.placeOrder(orderSummaryList)
                    } else {
                        scope.launch {
                            snackBarHostState.showSnackbar("Please enter delivery address")
                        }
                    }
                }
            }
        }
        if (orderStatus.value is UiState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        if (orderStatus.value is UiState.Success) {
            viewModel.clearOrder()
            mainViewModel.clearOrderSummary()
            navController.navigate(SCREEN_ORDER_SUCCESS)
        }
        SnackbarHost(hostState = snackBarHostState, modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun ColumnScope.OrderSummaryList(orderSummary: List<Product>?) {
    LazyColumn(
        modifier = Modifier
            .weight(80f)
            .fillMaxWidth()
            .background(color = Color.LightGray)
            .padding(top = 8.dp, start = 8.dp, end = 8.dp, bottom = 48.dp)
            .testTag("testTagOrderSummaryList"),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(orderSummary ?: listOf()) { product ->
            ProductRow(product)
        }
    }
}

@Composable
fun ProductRow(product: Product) {
    Card(elevation = 2.dp) {
        Row(
            modifier = Modifier
                .background(Color.White)
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = rememberImagePainter(data = product.image),
                contentDescription = product.name,
                modifier = Modifier.size(94.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(color = Color.White),
            ) {
                Text(text = product.name.orEmpty(), fontSize = 16.sp)
                Text(text = product.description.orEmpty(), fontSize = 12.sp)
                Text(text = "$${product.price}")
                Text(text = "Quantity: ${product.selected_quantity}")
            }
        }
    }
}

@Composable
fun ColumnScope.AddressBox(addressState: String, onValueChange: (String) -> Unit) {
    Card(
        elevation = 2.dp,
        modifier = Modifier
            .weight(2f)
            .fillMaxWidth()
    ) {
        TextField(
            value = addressState,
            onValueChange = { onValueChange(it) },
            label = { Text(stringResource(R.string.enter_delivery_address)) }
        )
    }

}

@Composable
fun ColumnScope.BottomBar(totalPrice: Int, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .weight(1f)
            .background(Color.LightGray)
            .padding(8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Total Price: $$totalPrice", modifier = Modifier.testTag("testTagTotalPrice"))
        Button(onClick = { onClick() }) {
            Text(text = stringResource(R.string.place_order))
        }
    }
}