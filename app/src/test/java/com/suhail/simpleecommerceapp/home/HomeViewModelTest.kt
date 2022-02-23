package com.suhail.simpleecommerceapp.home

import com.google.common.truth.Truth.assertThat
import com.suhail.simpleecommerceapp.data.Store
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
class HomeViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var homeScreenViewModel: HomeViewModel

    @Mock
    private lateinit var mockRepository: HomeRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        homeScreenViewModel = HomeViewModel(repository = mockRepository)
    }

    @Test
    fun `test getStoreDetails returns success`() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(mockRepository.getStoreDetails()).thenReturn(Result.success(Store()))
        homeScreenViewModel.getStoreDetails()
        val result = homeScreenViewModel.storeDetails
        assertThat(result.value).isInstanceOf(UiState.Success::class.java)
    }

    @Test
    fun `test getStoreDetails returns failure when error in repository`() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(mockRepository.getStoreDetails()).thenReturn(Result.failure(Throwable()))
        homeScreenViewModel.getStoreDetails()
        val result = homeScreenViewModel.storeDetails
        assertThat(result.value).isInstanceOf(UiState.Error::class.java)
    }

    @Test
    fun `test getProducts returns success`() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(mockRepository.getProducts()).thenReturn(Result.success(listOf()))
        homeScreenViewModel.getProducts()
        val result = homeScreenViewModel.products
        assertThat(result.value).isInstanceOf(UiState.Success::class.java)
    }

    @Test
    fun `test getProducts returns failure when error in repository`() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(mockRepository.getProducts()).thenReturn(Result.failure(Throwable()))
        homeScreenViewModel.getProducts()
        val result = homeScreenViewModel.products
        assertThat(result.value).isInstanceOf(UiState.Error::class.java)
    }

    @Test
    fun `test update quantity increments count by one`() {
        val update = homeScreenViewModel.updateQuantity(0, true, 2)
        assertThat(update).isEqualTo(1)
    }

    @Test
    fun `test update quantity decrements count by one`() {
        val update = homeScreenViewModel.updateQuantity(1, false, 2)
        assertThat(update).isEqualTo(0)
    }

    @Test
    fun `test update quantity won't decrement count less than zero`() {
        val update = homeScreenViewModel.updateQuantity(0, false, 2)
        assertThat(update).isEqualTo(0)
    }

    @Test
    fun `test update quantity won't increment count more than available quantity`() {
        val update = homeScreenViewModel.updateQuantity(2, true, 2)
        assertThat(update).isEqualTo(2)
    }

    @Test
    fun `test getOrderItems return empty by default`() {
        val list = homeScreenViewModel.getOrderItems()
        assertThat(list).isEmpty()
    }
}