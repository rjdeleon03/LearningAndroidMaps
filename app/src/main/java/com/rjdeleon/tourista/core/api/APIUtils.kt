package com.rjdeleon.tourista.core.api

import com.rjdeleon.tourista.Constants
import com.rjdeleon.tourista.data.serializable.NearbyPlace
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface GetNearbyPlacesListener {

    fun onReceive(results : List<NearbyPlace>)

    fun onError()
}

fun getNearbyPlaces(latitude : Double,
                    longitude : Double,
                    radius : Int,
                    type : String,
                    listener : GetNearbyPlacesListener) {

    val api = APIClient.client.create(MapAPI::class.java)
    api.getNearby(Constants.API_KEY_LOCATIONIQ, latitude, longitude, type, radius)
            .enqueue(object : Callback<List<NearbyPlace>> {
                override fun onFailure(call: Call<List<NearbyPlace>>, t: Throwable) {
                    listener.onError()
                }

                override fun onResponse(call: Call<List<NearbyPlace>>, response: Response<List<NearbyPlace>>) {

                    if (response.body() != null)
                        listener.onReceive(response.body()!!)
                }
            })
}