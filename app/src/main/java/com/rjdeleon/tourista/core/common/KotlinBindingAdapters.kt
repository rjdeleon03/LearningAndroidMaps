package com.rjdeleon.tourista.core.common

import android.graphics.BitmapFactory
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import com.bumptech.glide.Glide
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView
import com.rjdeleon.tourista.data.PlacePoint
import java.lang.Exception
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

@BindingAdapter("imageFromId")
fun setImageFromId(imageView : ImageView, id : Int) {

    if (id == 0) return

    val context = imageView.context
    Glide.with(context)
            .load(context.resources?.getDrawable(id))
            .into(imageView)
}

@BindingAdapter("android:text")
fun setTextFromDouble(editText : EditText, value : Double) {

    editText.setText(value.toString())
}

@InverseBindingAdapter(attribute = "android:text", event = "android:textAttrChanged")
fun getDoubleFromText(editText : EditText) : Double {

    try {
        return editText.text.toString().toDouble()
    } catch (ex : Exception) {
        return 0.0
    }
}