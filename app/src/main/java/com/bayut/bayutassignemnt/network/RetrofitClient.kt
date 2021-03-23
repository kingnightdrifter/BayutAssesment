package com.katch.hi5.data.api


import com.bayut.bayutassignemnt.BuildConfig
import com.bayut.bayutassignemnt.app.ConstantValues.Companion.API_TOKEN
import com.bayut.bayutassignemnt.app.ConstantValues.Companion.BASE_URL

import com.katch.hi5.data.app.App
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {


    private val retrofit by lazy {
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.level = Level.HEADERS
            logging.level = Level.BODY
        } else {
            logging.level = Level.NONE
        }

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header(
                    "Authorization",
                    "Bearer $API_TOKEN"
                )
            val request = requestBuilder.build()
            chain.proceed(request)
        }

        httpClient.connectTimeout(60, TimeUnit.SECONDS)
        httpClient.readTimeout(60, TimeUnit.SECONDS)
        httpClient.retryOnConnectionFailure(true)
        httpClient.addNetworkInterceptor(logging)
        httpClient.cache(Cache(App.instance.cacheDir, 50 * 1024 * 1024))
        val okHttpClient = httpClient.build()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    val api: ApiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }
}