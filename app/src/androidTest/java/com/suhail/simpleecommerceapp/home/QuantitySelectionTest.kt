package com.suhail.simpleecommerceapp.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.test.core.app.ApplicationProvider
import com.suhail.simpleecommerceapp.MainActivity
import com.suhail.simpleecommerceapp.data.Product
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class QuantitySelectionTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var viewModel: HomeViewModel
    private val product = Product(selected_quantity = 0, available_quantity = 4)

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            viewModel = hiltViewModel()
            var textState by remember { mutableStateOf("0") }
            QuantitySelection(textState = textState, onClickQuantity = {
                val updatedQuantity = viewModel.updateQuantity(
                    product.selected_quantity,
                    it,
                    product.available_quantity
                )
                product.selected_quantity = updatedQuantity
                textState = updatedQuantity.toString()
            })
        }
    }

    @Test
    fun checkPlusButtonInCreasesQuantity() {
        composeRule.onNodeWithTag("testTagPlusButton").performClick()
        composeRule.onNodeWithTag("testTagQuantityText").assertTextEquals("1")
    }

    @Test
    fun checkMinusButtonDecreasesQuantity() {
        composeRule.onNodeWithTag("testTagPlusButton").performClick()
        composeRule.onNodeWithTag("testTagMinusButton").performClick()
        composeRule.onNodeWithTag("testTagQuantityText").assertTextEquals("0")
    }

    @Test
    fun checkMinusButtonNotDecreasesQuantityBelowZero() {
        composeRule.onNodeWithTag("testTagMinusButton").performClick()
        composeRule.onNodeWithTag("testTagQuantityText").assertTextEquals("0")
    }

    @Test
    fun checkPlusButtonNotIncrementsQuantityAboveMaxValue() {
        (0..5).forEach { _->
            composeRule.onNodeWithTag("testTagPlusButton").performClick()
        }
        composeRule.onNodeWithTag("testTagQuantityText").assertTextEquals("4")
    }
}