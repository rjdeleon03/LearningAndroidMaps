package com.rjdeleon.tourista.feature.myLocation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rjdeleon.tourista.core.api.GetNearbyPlacesListener
import com.rjdeleon.tourista.core.api.getNearbyPlaces
import com.rjdeleon.tourista.core.common.CustomMutableLiveData
import com.rjdeleon.tourista.data.PlacePoint
import com.rjdeleon.tourista.data.serializable.NearbyPlace
import com.rjdeleon.tourista.data.serializable.PlaceResult

class MyLocationViewModel(application: Application) : AndroidViewModel(application) {

    private val mPlacePoint : CustomMutableLiveData<PlacePoint>
    private val mNearbyPlaces : MutableLiveData<List<PlaceResult>>

    init {
        val mld = CustomMutableLiveData<PlacePoint>()
        mld.value = PlacePoint()
        mPlacePoint = mld
        mNearbyPlaces = MutableLiveData()
        mNearbyPlaces.value = ArrayList()
    }

    fun getPlacePoint() : LiveData<PlacePoint> {
        return mPlacePoint
    }

    fun setPlacePoint(latitude : Double, longitude : Double) {
        mPlacePoint.value?.updateLatLng(latitude, longitude)
    }

    fun getNearbyPlaces() : LiveData<List<PlaceResult>> {
        return mNearbyPlaces
    }

    fun initNearbyPlacesRetrieval(filter : String) {

        val placePoint = mPlacePoint.value ?: return
        getNearbyPlaces(placePoint.latitude, placePoint.longitude, 1500, filter,
                object : GetNearbyPlacesListener {
                    override fun onReceive(results: List<PlaceResult>) {
                        mNearbyPlaces.value = results
                    }

                    override fun onError() {
                        // TODO: Handle
                    }

                })
    }
}