package com.desarrollandoapp.testsoyyo.data.retrofit

import com.desarrollandoapp.testsoyyo.BuildConfig
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitBuilder {
    private const val BASE_URL = BuildConfig.SERVER_URL
    private const val BASE_KEY = BuildConfig.SERVER_KEY

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
        val newClient = client.newBuilder().addInterceptor { chain ->
            var request = chain.request()

            val url = request.url().newBuilder().addQueryParameter("api_key", BASE_KEY).build()
            val builder = request.newBuilder().url(url)

            request = builder.build()
            chain.proceed(request)
        }.build()

        return retrofit
            .newBuilder()
            .client(newClient)
            .build()
            .create(service)
    }

    fun getRequestBody(field: String?): RequestBody {
        return RequestBody.create(MediaType.parse("application/json"), field ?: "")
    }
}