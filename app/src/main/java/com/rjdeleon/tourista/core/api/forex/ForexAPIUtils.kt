package com.rjdeleon.tourista.core.api.forex

import com.google.gson.JsonElement
import com.rjdeleon.tourista.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

interface GetCurrencyConversionListener {

    fun onReceive(rate : Double)

    fun onError()
}

fun getCurrencyConversion(sourceCurrency : String,
                          destCurrency : String,
                          listener : GetCurrencyConversionListener) {

    val api = ForexAPIClient.client.create(ForexAPI::class.java)
    api.getConversionRate(sourceCurrency + "_" + destCurrency, 'y', Constants.API_KEY_FOREX)
            .enqueue(object : Callback<JsonElement> {
                override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                    listener.onError()
                }

                override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                    if (response.body() == null) return

                    try {
                        val respStr = response.body().toString()
                        val res = respStr.replace("}", "")
                                .split("val\":")
                        listener.onReceive(res[1].toDouble())

                    } catch (ex : Exception) {
                        listener.onError()
                    }
                }

            })
}