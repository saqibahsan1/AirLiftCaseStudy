package com.saqib.casestudy.network

import com.saqib.casestudy.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkCall {
    var instance: WebService? = null
        get() {
            if (field == null) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                val client = OkHttpClient.Builder()
                        .addInterceptor(logging)
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(15, TimeUnit.SECONDS)
                        .build()
                val retrofit = Retrofit.Builder()
                        .baseUrl(BuildConfig.SERVER_URL)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                field = retrofit.create(WebService::class.java)
            }
            return field
        }
        private set
}
