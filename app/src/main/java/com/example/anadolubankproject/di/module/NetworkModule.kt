package com.example.anadolubankproject.di.module

import com.example.anadolubankproject.BuildConfig
import com.example.anadolubankproject.constants.ApiUrls
import com.example.anadolubankproject.service.DashboradService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    companion object {
        private const val READ_TIME_OUT = 60.toLong()
        private const val CONNECT_TIME_OUT = 60.toLong()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient() =
            OkHttpClient.Builder().apply {
                if (BuildConfig.DEBUG)
                    addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }
                    .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                    .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                    .addInterceptor { chain ->
                        val original = chain.request()
                        val reqBuilder = original.newBuilder()
                        chain.proceed(reqBuilder.build())
                    }
                    .build()

    @Singleton
    @Provides
    fun provideService(okHttpClient: OkHttpClient) =
            Retrofit.Builder().baseUrl(ApiUrls.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build().create(DashboradService::class.java)
}