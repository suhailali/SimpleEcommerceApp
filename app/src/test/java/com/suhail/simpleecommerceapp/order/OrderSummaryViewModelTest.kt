package com.suhail.simpleecommerceapp.order

import com.google.common.truth.Truth.assertThat
import com.suhail.simpleecommerceapp.data.OrderStatus
import com.suhail.simpleecommerceapp.data.Product
import com.suhail.simpleecommerceapp.ui.util.UiState
import com.suhail.simpleecommerceapp.util.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class OrderSummaryViewModelTest {
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var orderSummaryViewModel: OrderSummaryViewModel

    @Mock
    private lateinit var mockRepository: OrderSummaryRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        orderSummaryViewModel = OrderSummaryViewModel(mockRepository)
    }

    @Test
    fun testGetTotalPriceGiveValidSum() {
        val product1 = Product(selected_quantity = 2, price = 100)
        val product2 = Product(selected_quantity = 3, price = 30)
        val list = listOf(product1, product2)
        val result = orderSummaryViewModel.getTotalPrice(list)
        assertThat(result).isEqualTo(290)
    }

    @Test
    fun testClearOrderResetState() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(mockRepository.placeOrder(Mockito.anyList())).thenReturn(
            Result.success(
                OrderStatus(isSuccess = true)
            )
        )
        orderSummaryViewModel.placeOrder(listOf())
        orderSummaryViewModel.clearOrder()
        val result = orderSummaryViewModel.orderStatus
        assertThat(result.value).isInstanceOf(UiState.Empty::class.java)
    }

    @Test
    fun testPaceOrderGivesSuccess() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(mockRepository.placeOrder(Mockito.anyList())).thenReturn(
            Result.success(
                OrderStatus(isSuccess = true)
            )
        )
        orderSummaryViewModel.placeOrder(listOf())
        val result = orderSummaryViewModel.orderStatus
        assertThat(result.value).isInstanceOf(UiState.Success::class.java)
    }

    @Test
    fun testPaceOrderGivesError() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(mockRepository.placeOrder(Mockito.anyList())).thenReturn(
            Result.failure(
                Throwable()
            )
        )
        orderSummaryViewModel.placeOrder(listOf())
        val result = orderSummaryViewModel.orderStatus
        assertThat(result.value).isInstanceOf(UiState.Error::class.java)
    }
}