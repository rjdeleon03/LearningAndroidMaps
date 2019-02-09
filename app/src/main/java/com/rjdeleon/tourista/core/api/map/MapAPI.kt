package com.rjdeleon.tourista.core.api.map

import com.rjdeleon.tourista.data.serializable.NearbyPlace
import com.rjdeleon.tourista.data.serializable.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MapAPI {

    @GET("nearby.php")
    fun getNearby(@Query("key") key : String,
                  @Query("lat") latitude : Double,
                  @Query("lon") longitude : Double,
                  @Query("tag") tag : String,
                  @Query("radius") radius : Int) : Call<List<NearbyPlace>>

    @GET("discover/explore")
    fun getNearbyPlaces(@Query("at") location : String,
                        @Query("cat") type: String,
                        @Query("app_id") appId : String,
                        @Query("app_code") appCode : String) : Call<PlaceResponse>
}