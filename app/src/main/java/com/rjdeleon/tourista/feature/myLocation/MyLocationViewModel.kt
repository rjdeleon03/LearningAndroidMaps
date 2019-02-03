package com.rjdeleon.tourista.feature.myLocation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.rjdeleon.tourista.core.common.CustomMutableLiveData
import com.rjdeleon.tourista.data.PlacePoint

class MyLocationViewModel(application: Application) : AndroidViewModel(application) {

    private val mPlacePoint : CustomMutableLiveData<PlacePoint>

    init {
        val mld = CustomMutableLiveData<PlacePoint>()
        mld.value = PlacePoint()
        mPlacePoint = mld
    }

    fun getPlacePoint() : LiveData<PlacePoint> {
        return mPlacePoint
    }

    fun setPlacePoint(latitude : Double, longitude : Double) {
        mPlacePoint.value?.updateLatLng(latitude, longitude);
    }
}