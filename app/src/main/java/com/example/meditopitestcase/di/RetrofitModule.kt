package com.example.meditopitestcase.di

import com.example.meditopitestcase.api.Api
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
open class RetrofitModule {
    private val contentType = "application/json".toMediaType()

    private val BASE_URL = "https://media.meditopia.com/files/"

    @ExperimentalSerializationApi
    @Provides
    @Singleton
    fun provideRetrofitJson(OkHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(OkHttpClient)
            .addConverterFactory(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                }
                    .asConverterFactory(contentType)
            )
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideJsonApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }


    @Provides
    @Singleton
    fun provideOkHttp(builder: OkHttpClient.Builder): OkHttpClient {
        return builder.build()
    }

    @Provides
    @Singleton
    fun setLogLevel(): HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.HEADERS

    @Provides
    @Singleton
    fun setupOkHttpBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .also {
                        it.level = setLogLevel()
                    }
            )
            .followRedirects(true)
            .followSslRedirects(true)
            .readTimeout(40, TimeUnit.SECONDS)
            .connectTimeout(40, TimeUnit.SECONDS)
            .writeTimeout(40, TimeUnit.SECONDS)
    }

}