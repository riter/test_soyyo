package com.desarrollandoapp.testsoyyo.data.retrofit

import com.desarrollandoapp.testsoyyo.BuildConfig
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitBuilder {
    private const val BASE_URL = BuildConfig.SERVER_URL

    private val client: OkHttpClient by lazy {
        buildClient()
    }

    val retrofit: Retrofit by lazy {
        buildRetrofit(client)
    }

    private fun buildClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor { chain ->
                var request = chain.request()
                val builder = request.newBuilder()
                    .addHeader("Accept", "Application/json")
                    .addHeader("Connection", "close")
                request = builder.build()
                chain.proceed(request)
            }

        if (BuildConfig.DEBUG) {
            builder.addNetworkInterceptor(StethoInterceptor())
        }

        return builder.build()
    }

    private fun buildRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    fun <T> createServiceWithAuth(service: Class<T>): T {
        return retrofit.create(service)
    }
}