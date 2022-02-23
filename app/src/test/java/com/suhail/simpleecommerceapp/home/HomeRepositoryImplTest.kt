package com.suhail.simpleecommerceapp.home

import android.content.Context
import com.google.common.truth.Truth.assertThat
import com.suhail.simpleecommerceapp.service.MockServiceImpl
import com.suhail.simpleecommerceapp.util.FileReaderTestImpl
import com.suhail.simpleecommerceapp.util.JsonDataProvider
import com.suhail.simpleecommerceapp.util.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeRepositoryImplTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var homeRepositoryImpl: HomeRepositoryImpl

    @Before
    fun setUp() {
        homeRepositoryImpl = HomeRepositoryImpl(
            MockServiceImpl(
                JsonDataProvider(FileReaderTestImpl())))
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testFetchingStoreDetailsIsSuccess() {
       mainCoroutineRule.runBlockingTest {
            val result = homeRepositoryImpl.getStoreDetails()
            assertThat(result.isSuccess)
        }
    }

    @Test
    fun getProducts() {
    }
}