package com.rjdeleon.tourista.core.api

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface GetNearbyPlacesListener {

    fun onReceive (results : List<Result>)

    fun onError()
}

fun getNearbyPlaces(location : String,
                    radius : Int,
                    type : String,
                    keyword : String,
                    key : String,
                    listener : GetNearbyPlacesListener) {

    val api = APIClient.client.create(GoogleMapAPI::class.java)
    api.getNearby(location, radius, type, keyword, key)
            .enqueue(object : Callback<PlacesResults> {
                override fun onResponse(call: Call<PlacesResults>, response: Response<PlacesResults>) {
                    listener.onReceive(response.body()?.results!!)
                }

                override fun onFailure(call: Call<PlacesResults>, t: Throwable) {
                    Log.wtf("APIUtils", "Getting nearby places failed!")
                    listener.onError()
                }
            })
}