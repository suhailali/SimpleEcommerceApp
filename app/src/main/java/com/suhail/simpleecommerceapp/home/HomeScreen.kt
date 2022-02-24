package com.suhail.simpleecommerceapp.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.suhail.simpleecommerceapp.MainViewModel
import com.suhail.simpleecommerceapp.R
import com.suhail.simpleecommerceapp.SCREEN_ORDER_SUMMARY
import com.suhail.simpleecommerceapp.data.Product
import com.suhail.simpleecommerceapp.data.Store
import com.suhail.simpleecommerceapp.ui.util.UiState
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    mainViewModel: MainViewModel,
    navController: NavHostController
) {

    val storeDetailState = viewModel.storeDetails
    val storeProductState = viewModel.products
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
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
                Products(productList, viewModel = viewModel) {
                    scope.launch {
                        snackBarHostState.showSnackbar("Reached maximum order quantity for the product.")
                    }
                }
            }
        }
        OrderSummary {
            val orderItems = viewModel.getOrderItems()
            mainViewModel.setOrderSummary(orderItems)
            navController.navigate(SCREEN_ORDER_SUMMARY)
        }
        SnackbarHost(hostState = snackBarHostState, modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun StoreInfo(store: Store) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(color = Color.Gray)
            .padding(10.dp)
            .testTag("TestTagStoreInfo")
    ) {
        Image(
            painter = rememberImagePainter(data = store.image),
            contentDescription = store.name,
            modifier = Modifier
                .align(Alignment.CenterEnd)
        )
        Column(modifier = Modifier.align(Alignment.CenterStart)) {
            Text(text = store.name.orEmpty(), color = Color.White, fontSize = 18.sp)
            Text(text = store.open_time.orEmpty(), color = Color.White, fontSize = 12.sp)
            Text(text = store.address.orEmpty(), color = Color.White, fontSize = 12.sp)
        }
    }
}

@Composable
fun Products(productList: List<Product>, viewModel: HomeViewModel, onMaxQuantityReached:()->Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.LightGray)
            .padding(top = 8.dp, start = 8.dp, end = 8.dp, bottom = 48.dp)
            .testTag("TestTagProductList"),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(productList) { product ->
            var textState by remember { mutableStateOf(product.selected_quantity.toString()) }
            ProductRow(product = product, textState) {
                run {
                    val updatedQuantity = viewModel.updateQuantity(
                        product.selected_quantity,
                        it,
                        product.available_quantity
                    )
                    if(product.selected_quantity != updatedQuantity) {
                        product.selected_quantity = updatedQuantity
                        textState = updatedQuantity.toString()
                    } else {
                        if (product.selected_quantity != 0) onMaxQuantityReached()
                    }
                }
            }
        }
    }
}

@Composable
fun ProductRow(product: Product, quantityState: String, onClickQuantity: (Boolean) -> Unit) {
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
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "$${product.price}")
                    Spacer(modifier = Modifier.width(10.dp))
                    QuantitySelection(quantityState, onClickQuantity)
                }
            }
        }
    }
}

@Composable
fun QuantitySelection(textState: String, onClickQuantity: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.border(1.dp, MaterialTheme.colors.primary),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextButton(modifier = Modifier.testTag("testTagMinusButton"), onClick = {
            onClickQuantity(false)
        }) {
            Text(text = "-", fontSize = 25.sp)
        }
        Text(
            text = textState, modifier = Modifier
                .fillMaxHeight()
                .testTag("testTagQuantityText"), textAlign = TextAlign.Center
        )
        TextButton(modifier = Modifier.testTag("testTagPlusButton"),onClick = {
            onClickQuantity(true)
        }) {
            Text(text = "+", fontSize = 16.sp)
        }
    }
}

@Composable
fun BoxScope.OrderSummary(onClick: () -> Unit) {
    Box(
        Modifier
            .fillMaxWidth()
            .background(Color.Gray)
            .padding(4.dp)
            .height(40.dp)
            .align(Alignment.BottomCenter),
    ) {
        Button(modifier = Modifier.align(Alignment.BottomCenter), onClick = { onClick() }) {
            Text(text = stringResource(R.string.order_summary), modifier = Modifier.testTag("testTagButton"))
        }
    }
}