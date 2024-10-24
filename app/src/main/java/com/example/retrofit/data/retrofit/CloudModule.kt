package com.example.retrofit.data.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface CloudModule {

    fun <T> service(clasz: Class<T>, http: String): T

    class Base(): CloudModule {
        override fun <T> service(clasz: Class<T>, http: String): T {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY


            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val retrofit = Retrofit.Builder()
                .baseUrl(http).client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(clasz)
        }

    }
}