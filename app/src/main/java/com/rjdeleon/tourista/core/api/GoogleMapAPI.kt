package com.rjdeleon.tourista.core.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleMapAPI {

    @GET("place/nearbysearch/json")
    fun getNearby(@Query("location") location : String,
                  @Query("radius") radius : Int,
                  @Query("type") type : String,
                  @Query("keyword") keyword : String,
                  @Query("key") key : String) : Call<PlacesResults>
}