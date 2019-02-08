package com.rjdeleon.tourista.core.api.forex

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object ForexAPIClient {

    private var retrofit : Retrofit? = null

    val client : Retrofit
        get() {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                    .addInterceptor(interceptor).build()
            retrofit = Retrofit.Builder()
                    .baseUrl("https://www.freeforexapi.com/api/live")
                    .client(client)
                    .build()
            return retrofit as Retrofit
        }

}