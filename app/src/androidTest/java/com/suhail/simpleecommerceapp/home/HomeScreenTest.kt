package com.suhail.simpleecommerceapp.home

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.suhail.simpleecommerceapp.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.Rule

class HomeScreenTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()
}