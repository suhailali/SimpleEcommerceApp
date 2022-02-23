package com.suhail.simpleecommerceapp.order

import com.google.common.truth.Truth.assertThat
import com.suhail.simpleecommerceapp.service.MockServiceImpl
import com.suhail.simpleecommerceapp.util.FileReaderTestImpl
import com.suhail.simpleecommerceapp.util.FileWriterTestImpl
import com.suhail.simpleecommerceapp.util.MainCoroutineRule
import com.suhail.simpleecommerceapp.util.jsonconverter.JsonDataHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class OrderSummaryRepositoryImplTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var orderSummaryRepository: OrderSummaryRepository

    @Before
    fun setUp() {
        orderSummaryRepository = OrderSummaryRepositoryImpl(
            MockServiceImpl(
                JsonDataHandler(FileReaderTestImpl()),
                FileWriterTestImpl()
            )
        )
    }

    @Test
    fun testPlacingOrderIsSuccess() = mainCoroutineRule.runBlockingTest {
        val result = orderSummaryRepository.placeOrder(listOf())
        assertThat(result.isSuccess)
    }
}