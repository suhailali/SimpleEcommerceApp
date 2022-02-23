package com.suhail.simpleecommerceapp.util.jsonconverter

import com.google.common.truth.Truth.assertThat
import com.suhail.simpleecommerceapp.data.Product
import com.suhail.simpleecommerceapp.data.Store
import com.suhail.simpleecommerceapp.util.FileReaderTestImpl
import com.suhail.simpleecommerceapp.util.filereader.FileReader
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.internal.matchers.Any

class JsonDataHandlerTest{

    private lateinit var jsonDataHandler: JsonDataHandler

    @Mock
    private lateinit var mockFileReader: FileReader

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `test get store details returns valid store data class`() {
        jsonDataHandler = JsonDataHandler(FileReaderTestImpl())
        val data = jsonDataHandler.getStoreDetails("data/StoreInfo.json")
        assertThat(data).isInstanceOf(Store::class.java)
    }

    @Test
    fun `test get store details returns null for wrong path`() {
        jsonDataHandler = JsonDataHandler(mockFileReader)
        Mockito.`when`(mockFileReader.loadJSONFile(Mockito.anyString())).thenReturn(null)
        val data = jsonDataHandler.getStoreDetails("data1/StoreInfo.json")
        assertThat(data).isNull()
    }

    @Test
    fun `test get products returns valid store data class`() {
        jsonDataHandler = JsonDataHandler(FileReaderTestImpl())
        val data = jsonDataHandler.getProducts("data/Products.json")
        assertThat(data).isInstanceOf(List::class.java)
    }

    @Test
    fun `test get products returns empty for wrong path`() {
        jsonDataHandler = JsonDataHandler(mockFileReader)
        Mockito.`when`(mockFileReader.loadJSONFile(Mockito.anyString())).thenReturn(null)
        val data = jsonDataHandler.getProducts("data1/StoreInfo.json")
        assertThat(data).isEmpty()
    }

    @Test
    fun `test passing a product list returns a string`() {
        val product = Product()
        val list = listOf(product)
        jsonDataHandler = JsonDataHandler(mockFileReader)
        val data = jsonDataHandler.getJsonForOrder(list)
        assertThat(data).isInstanceOf(String::class.java)
        assertThat(data).isNotEmpty()
    }
}