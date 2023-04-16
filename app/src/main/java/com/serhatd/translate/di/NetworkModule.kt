package com.serhatd.translate.di

import com.serhatd.translate.data.retrofit.TranslateService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                // Add custom headers to each request
                val request = chain.request().newBuilder()
                    .addHeader("X-RapidAPI-Key", "key")
                    .addHeader("X-RapidAPI-Host", "deep-translate1.p.rapidapi.com")
                    .build()
                chain.proceed(request)
            }.build()
    }

    @Singleton
    @Provides
    fun provideRetrofitClient(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://deep-translate1.p.rapidapi.com/language/translate/")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideTranslateService(retrofit: Retrofit): TranslateService {
        return retrofit.create(TranslateService::class.java)
    }
}