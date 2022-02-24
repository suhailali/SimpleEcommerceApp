package com.suhail.simpleecommerceapp.home

import com.google.common.truth.Truth.assertThat
import com.suhail.simpleecommerceapp.service.MockServiceImpl
import com.suhail.simpleecommerceapp.util.FileReaderTestImpl
import com.suhail.simpleecommerceapp.util.FileWriterTestImpl
import com.suhail.simpleecommerceapp.util.MainCoroutineRule
import com.suhail.simpleecommerceapp.util.jsonconverter.JsonDataHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class HomeRepositoryImplTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var homeRepositoryImpl: HomeRepositoryImpl

    @Before
    fun setUp() {
        homeRepositoryImpl = HomeRepositoryImpl(
            MockServiceImpl(
                JsonDataHandler(FileReaderTestImpl()),
                FileWriterTestImpl()
            )
        )
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
    fun testFetchingProductsIsSuccess() {
        mainCoroutineRule.runBlockingTest {
            val result = homeRepositoryImpl.getProducts()
            assertThat(result.isSuccess)
        }
    }
}