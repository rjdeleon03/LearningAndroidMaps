package com.rjdeleon.tourista.core.api.forex

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface  ForexAPI {

    @GET("convert")
    fun getConversionRate(@Query("q") convCode : String,
                          @Query("compact") isCompact : Char) : Call<JsonElement>
}