package com.example.dyma_chap8.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://my-json-server.typicode.com/"

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(LogginInterceptor())
        .build()

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

class LogginInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val time1 = System.nanoTime()

        Log.d("Request sending", "Sending request: ${request.url} \n Request reception time: ${time1} \n Request headers: ${request.headers}")


        val response: Response = chain.proceed(request)
        val time2 = System.nanoTime()

        Log.d("Received response", "Response for URL: ${response.request.url}, took ${(time2 - time1) / 1e6}ms")

        return response
    }

}