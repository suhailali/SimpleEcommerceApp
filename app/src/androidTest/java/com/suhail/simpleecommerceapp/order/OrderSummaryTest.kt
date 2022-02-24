package com.suhail.simpleecommerceapp.order

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import com.suhail.simpleecommerceapp.MainActivity
import com.suhail.simpleecommerceapp.MainViewModel
import com.suhail.simpleecommerceapp.Screen
import com.suhail.simpleecommerceapp.data.Product
import com.suhail.simpleecommerceapp.home.HomeScreen
import com.suhail.simpleecommerceapp.home.HomeViewModel
import com.suhail.simpleecommerceapp.ui.theme.SimpleEcommerceAppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class OrderSummaryTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var viewModel: OrderSummaryViewModel
    private lateinit var viewModelMain: MainViewModel

    @OptIn(ExperimentalFoundationApi::class)
    @Before
    fun setUp() {
        hiltRule.inject()
        viewModelMain = MainViewModel()
        val product1 = Product(selected_quantity = 2, price = 100)
        val product2 = Product(selected_quantity = 3, price = 30)
        val list = listOf(product1, product2)
        viewModelMain.setOrderSummary(list)
        composeRule.setContent {
            val navController = rememberNavController()
            SimpleEcommerceAppTheme {
                NavHost(
                    navController = navController,
                    startDestination = Screen.Order.route
                ) {
                    composable(route = Screen.Order.route) {
                        viewModel = hiltViewModel()
                        OrderSummary(
                            viewModel = viewModel,
                            mainViewModel = viewModelMain,
                            navController = navController
                        )
                    }
                }
            }
        }
    }

    @Test
    fun testOrderSummaryLisIsDisplayed() {
        composeRule.onNodeWithTag("testTagOrderSummaryList").assertIsDisplayed()
    }

    @Test
    fun testTotalPriceDisplayedIsCorrect() {
        val total = 290
        composeRule.onNodeWithTag("testTagTotalPrice").assertTextEquals("Total Price: $$total")
    }
}