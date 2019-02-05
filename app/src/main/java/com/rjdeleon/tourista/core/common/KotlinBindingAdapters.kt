package com.rjdeleon.tourista.core.common

import android.graphics.BitmapFactory
import androidx.databinding.BindingAdapter
import com.google.android.gms.maps.CameraUpdateFactory
import com.mapbox.api.geocoding.v5.models.CarmenFeature
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView
import com.rjdeleon.tourista.data.PlacePoint
import com.rjdeleon.tourista.data.serializable.PlaceResult


@BindingAdapter("mapPlacePoint")
fun setMapPlacePoint(mapView: MapView, placePoint: PlacePoint) {

//    mapView.getMapAsync {
//        it.clear()
//
//        val latLng = LatLng(placePoint.latitude, placePoint.longitude)
//        it.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 8.0f))
//        it.addMarker(MarkerOptions()
//                .position(latLng)
//                .title("Your location"))
//    }

}

@BindingAdapter("mapNearbyPlaces")
fun setMapNearbyPlaces(mapView : MapView, results : List<PlaceResult>) {

    mapView.getMapAsync {
        it.clear()

        for (place in results) {

            if (place.position != null && place.position.size == 2) {
                it.addMarker(MarkerOptions()
                        .position(LatLng(place.position[0], place.position[1]))
                        .title(place.name))
            }
        }
    }
}