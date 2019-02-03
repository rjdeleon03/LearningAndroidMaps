package com.rjdeleon.tourista.core.common

import androidx.databinding.BindingAdapter
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.rjdeleon.tourista.core.api.Result
import com.rjdeleon.tourista.data.PlacePoint


@BindingAdapter("mapPlacePoint")
fun setMapPlacePoint(mapView: MapView, placePoint: PlacePoint) {

    mapView.getMapAsync {
        it.clear()

        val latLng = LatLng(placePoint.latitude, placePoint.longitude)
        it.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 8.0f))
        it.addMarker(MarkerOptions()
                .position(latLng)
                .title("Your location"))
    }
}

@BindingAdapter("mapNearbyPlaces")
fun setMapNearbyPlaces(mapView : MapView, results : List<Result>) {

    mapView.getMapAsync {
        it.clear()

        for (place in results) {
            val location = place.geometry?.location ?: continue

            val latLng = LatLng(location.lat!!, location.lng!!)
            it.addMarker(MarkerOptions()
                    .position(latLng)
                    .title(place.name))
        }
    }
}