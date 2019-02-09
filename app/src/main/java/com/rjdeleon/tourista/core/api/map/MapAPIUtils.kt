package com.rjdeleon.tourista.core.api.map

import com.rjdeleon.tourista.Constants
import com.rjdeleon.tourista.data.serializable.PlaceResponse
import com.rjdeleon.tourista.data.serializable.PlaceResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface GetNearbyPlacesListener {

    fun onReceive(results : List<PlaceResult>)

    fun onError()
}

fun getNearbyPlaces(latitude : Double,
                    longitude : Double,
                    radius : Int,
                    type : String,
                    listener : GetNearbyPlacesListener) {

    val location = "$latitude,$longitude"
    val api = MapAPIClient.client.create(MapAPI::class.java)
    api.getNearbyPlaces(location, type, Constants.API_KEY_HERE_ID, Constants.API_KEY_HERE_CODE)
            .enqueue(object : Callback<PlaceResponse> {
                override fun onFailure(call: Call<PlaceResponse>, t: Throwable) {
                    listener.onError()
                }

                override fun onResponse(call: Call<PlaceResponse>, response: Response<PlaceResponse>) {
                    val resultItems = response.body()?.result?.items

                    if (resultItems != null)
                        listener.onReceive(resultItems)
                }
            })
    }
