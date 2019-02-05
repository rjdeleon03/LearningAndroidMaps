package com.rjdeleon.tourista.core.api

import android.util.Log
import com.mapbox.api.geocoding.v5.MapboxGeocoding
import com.mapbox.api.geocoding.v5.models.CarmenFeature
import com.mapbox.api.geocoding.v5.models.GeocodingResponse
import com.rjdeleon.tourista.Constants
import com.rjdeleon.tourista.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface GetNearbyPlacesListener {

    fun onReceive (results : List<CarmenFeature>)

    fun onError()
}

fun searchNearbyPlaces(latitude : Double, longitude : Double,
                       radius : Int, filter : String, listener : GetNearbyPlacesListener) {

    val convertedRadius = radius / 11111.0
    val startLat = latitude - convertedRadius
    val startLng = longitude - convertedRadius
    val endLat = latitude + convertedRadius
    val endLng = longitude + convertedRadius

    val mbg = MapboxGeocoding.builder()
            .accessToken(Constants.API_KEY_MAPBOX)
            .query(filter)
            .bbox(startLng, startLat, endLng, endLat)
            .build()
    mbg.enqueueCall(object : Callback<GeocodingResponse> {
        override fun onFailure(call: Call<GeocodingResponse>, t: Throwable) {
            listener.onError()
            t.printStackTrace()
        }

        override fun onResponse(call: Call<GeocodingResponse>, response: Response<GeocodingResponse>) {
            val data = response.body()?.features()

            if (data != null)
                listener.onReceive(data)
        }

    })
}