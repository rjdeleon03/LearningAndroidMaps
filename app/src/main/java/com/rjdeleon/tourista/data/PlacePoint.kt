package com.rjdeleon.tourista.data

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class PlacePoint(private var _latitude: Double = 0.0,
                 private var _longitude: Double = 0.0) : BaseObservable() {

    var latitude : Double
        @Bindable get() = _latitude
        set(value) {
            _latitude = value
        }

    var longitude : Double
        @Bindable get() = _longitude
        set(value) {
            _longitude = value
        }

    fun updateLatLng(lat : Double, lng : Double) {
        latitude = lat
        longitude = lng
        notifyChange()
    }
}