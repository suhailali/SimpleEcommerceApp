package com.suhail.simpleecommerceapp.home

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import com.suhail.simpleecommerceapp.MainActivity
import com.suhail.simpleecommerceapp.MainViewModel
import com.suhail.simpleecommerceapp.Screen
import com.suhail.simpleecommerceapp.ui.theme.SimpleEcommerceAppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class HomeScreenTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var context: Context
    private lateinit var viewModel: HomeViewModel
    private lateinit var viewModelMain: MainViewModel

    @OptIn(ExperimentalFoundationApi::class)
    @Before
    fun setUp() {
        hiltRule.inject()
        context = ApplicationProvider.getApplicationContext()
        viewModelMain = MainViewModel()
        composeRule.setContent {
            val navController = rememberNavController()
            SimpleEcommerceAppTheme {
                NavHost(
                    navController = navController,
                    startDestination = Screen.Home.route
                ) {
                    composable(route = Screen.Home.route) {
                        viewModel = hiltViewModel()
                        viewModel.getStoreDetails()
                        viewModel.getProducts()
                        HomeScreen(navController = navController, viewModel = viewModel, mainViewModel = viewModelMain)
                    }
                }
            }
        }
    }

    @Test
    fun test_store_detail_is_displayed() {
        composeRule.onNodeWithTag("TestTagStoreInfo").assertIsDisplayed()
    }

    @Test
    fun test_product_list_is_displayed() {
        composeRule.onNodeWithTag("TestTagProductList").assertIsDisplayed()
    }
}