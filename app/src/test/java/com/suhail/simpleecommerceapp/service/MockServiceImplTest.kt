package com.suhail.simpleecommerceapp.service

import com.google.common.truth.Truth.assertThat
import com.suhail.simpleecommerceapp.data.Store
import com.suhail.simpleecommerceapp.util.MainCoroutineRule
import com.suhail.simpleecommerceapp.util.filewriter.FileWriter
import com.suhail.simpleecommerceapp.util.jsonconverter.DataHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MockServiceImplTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var mockJsonDataHandler: DataHandler

    @Mock
    private lateinit var mockFileWriter: FileWriter

    private lateinit var serviceImpl: DataService

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        serviceImpl =
            MockServiceImpl(jsonDataHandler = mockJsonDataHandler, fileWriter = mockFileWriter)
    }

    @Test
    fun `test get store details return success if data from file is valid`() =
        mainCoroutineRule.runBlockingTest {
            Mockito.`when`(mockJsonDataHandler.getStoreDetails(Mockito.anyString()))
                .thenReturn(Store())
            val data = serviceImpl.getStoreDetails()
            assertThat(data.isSuccess)
        }

    @Test
    fun `test get store details return failure if data from file is invalid`() =
        mainCoroutineRule.runBlockingTest {
            Mockito.`when`(mockJsonDataHandler.getStoreDetails(Mockito.anyString()))
                .thenReturn(null)
            val data = serviceImpl.getStoreDetails()
            assertThat(data.isFailure)
        }

    @Test
    fun `test get products return success if data from file is valid`() =
        mainCoroutineRule.runBlockingTest {
            Mockito.`when`(mockJsonDataHandler.getProducts(Mockito.anyString()))
                .thenReturn(listOf())
            val data = serviceImpl.getProducts()
            assertThat(data.isSuccess)
        }

    @Test
    fun `test get products return failure if data from file is invalid`() =
        mainCoroutineRule.runBlockingTest {
            Mockito.`when`(mockJsonDataHandler.getProducts(Mockito.anyString()))
                .thenReturn(null)
            val data = serviceImpl.getProducts()
            assertThat(data.isFailure)
        }

    @Test
    fun `test post order return success if write to file is success`() =
        mainCoroutineRule.runBlockingTest {
            Mockito.`when`(mockJsonDataHandler.getJsonForOrder(Mockito.anyList()))
                .thenReturn(Mockito.anyString())
            Mockito.`when`(mockFileWriter.writeToFile("FileName", Mockito.anyString()))
                .thenReturn(true)
            val data = serviceImpl.postOrder(listOf())
            assertThat(data.isSuccess)
        }

    @Test
    fun `test post order return failure if write to file is failed`() =
        mainCoroutineRule.runBlockingTest {
            Mockito.`when`(mockJsonDataHandler.getJsonForOrder(Mockito.anyList()))
                .thenReturn(Mockito.anyString())
            Mockito.`when`(mockFileWriter.writeToFile("FileName", Mockito.anyString()))
                .thenReturn(false)
            val data = serviceImpl.postOrder(listOf())
            assertThat(data.isFailure)
        }
}