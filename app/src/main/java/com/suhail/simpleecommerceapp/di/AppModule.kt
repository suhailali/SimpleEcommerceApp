package com.suhail.simpleecommerceapp.di

import android.content.Context
import com.suhail.simpleecommerceapp.home.HomeRepository
import com.suhail.simpleecommerceapp.home.HomeRepositoryImpl
import com.suhail.simpleecommerceapp.order.OrderSummaryRepository
import com.suhail.simpleecommerceapp.order.OrderSummaryRepositoryImpl
import com.suhail.simpleecommerceapp.service.DataService
import com.suhail.simpleecommerceapp.service.MockServiceImpl
import com.suhail.simpleecommerceapp.util.filereader.FileReader
import com.suhail.simpleecommerceapp.util.filereader.FileReaderImpl
import com.suhail.simpleecommerceapp.util.jsonconverter.JsonDataHandler
import com.suhail.simpleecommerceapp.util.filewriter.FileWriter
import com.suhail.simpleecommerceapp.util.filewriter.LocalStorageFileWriter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideDataService(jsonDataHandler: JsonDataHandler, fileWriter: FileWriter) : DataService {
        return MockServiceImpl(jsonDataHandler = jsonDataHandler, fileWriter = fileWriter)
    }

    @Singleton
    @Provides
    fun providesJsonDataHandler(fileReader: FileReader): JsonDataHandler {
        return JsonDataHandler(fileReader = fileReader)
    }

    @Singleton
    @Provides
    fun providesFileReader(@ApplicationContext appContext: Context): FileReader {
        return FileReaderImpl(context = appContext)
    }

    @Singleton
    @Provides
    fun providesFileWriter(@ApplicationContext appContext: Context): FileWriter {
        return LocalStorageFileWriter(appContext)
    }
}


@Module
@InstallIn(ViewModelComponent::class)
object HomeModule {
    @Provides
    fun provideHomeRepository(dataService: DataService): HomeRepository {
        return HomeRepositoryImpl(dataService = dataService)
    }
}

@Module
@InstallIn(ViewModelComponent::class)
object OrderModule {
    @Provides
    fun provideOrderSummaryRepository(dataService: DataService): OrderSummaryRepository {
        return OrderSummaryRepositoryImpl(dataService = dataService)
    }
}