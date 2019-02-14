package com.rjdeleon.tourista.core.api.forex

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ForexAPIClient {

    private var retrofit : Retrofit? = null

    val client : Retrofit
        get() {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            retrofit = Retrofit.Builder()
                    .baseUrl("https://free.currencyconverterapi.com/api/v6/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit as Retrofit
        }

}