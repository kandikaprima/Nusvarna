package com.dicoding.picodiploma.nusvarna.api

import androidx.viewbinding.BuildConfig
import com.dicoding.picodiploma.nusvarna.utils.PreferenceHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        fun getApiService(): ApiService {
            val client = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val original = chain.request()
                    val token = PreferenceHelper().getToken()
                    val requestBuilder = original.newBuilder()
                        .header("Authorization", token.toString())
                        .method(original.method, original.body)
                    val request = requestBuilder.build()
                    chain.proceed(request)
                }
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://nusvarna-predict-mdu7ait23q-et.a.run.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiService::class.java)
        }

    }

}