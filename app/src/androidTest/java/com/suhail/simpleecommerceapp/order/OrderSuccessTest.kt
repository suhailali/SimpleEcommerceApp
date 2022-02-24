package com.suhail.simpleecommerceapp.order

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.suhail.simpleecommerceapp.MainActivity
import com.suhail.simpleecommerceapp.Screen
import com.suhail.simpleecommerceapp.ui.theme.SimpleEcommerceAppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class OrderSuccessTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @OptIn(ExperimentalFoundationApi::class)
    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            val navController = rememberNavController()
            SimpleEcommerceAppTheme {
                NavHost(
                    navController = navController,
                    startDestination = Screen.Order.route
                ) {
                    composable(route = Screen.Order.route) {
                        OrderSuccess(navController = navController)
                    }
                }
            }
        }
    }

    @Test
    fun testOrderSuccessTextIsDisplayed() {
        composeRule.onNodeWithTag("testTagCongrats").assertIsDisplayed()
    }
}