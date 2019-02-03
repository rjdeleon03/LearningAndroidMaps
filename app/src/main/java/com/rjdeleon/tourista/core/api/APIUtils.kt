package com.rjdeleon.tourista.core.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


fun getNearbyPlaces(location : String,
                    radius : Int,
                    type : String,
                    keyword : String,
                    key : String) : LiveData<List<String>> {

    val responseData = MutableLiveData<List<String>>()
    val api = APIClient.client.create(GoogleMapAPI::class.java)
    api.getNearby(location, radius, type, keyword, key)
            .enqueue(object : Callback<PlacesResults> {
                override fun onResponse(call: Call<PlacesResults>, response: Response<PlacesResults>) {
                    var x = 5
                }

                override fun onFailure(call: Call<PlacesResults>, t: Throwable) {
                    Log.wtf("APIUtils", "Getting nearby places failed!")
                }
            })
    return responseData
}