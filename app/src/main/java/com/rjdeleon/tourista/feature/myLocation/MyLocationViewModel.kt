package com.rjdeleon.tourista.feature.myLocation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rjdeleon.tourista.core.api.GetNearbyPlacesListener
import com.rjdeleon.tourista.core.api.Result
import com.rjdeleon.tourista.core.common.CustomMutableLiveData
import com.rjdeleon.tourista.data.PlacePoint

class MyLocationViewModel(application: Application) : AndroidViewModel(application) {

    private val mPlacePoint : CustomMutableLiveData<PlacePoint>
    private val mNearbyPlaces : MutableLiveData<List<Result>>

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

    fun getNearbyPlaces() : LiveData<List<Result>> {
        return mNearbyPlaces
    }

    fun initNearbyPlacesRetrieval(filter : String) {

        val placePoint = mPlacePoint.value ?: return
        val location = placePoint.latitude.toString() + "," + placePoint.longitude.toString()
        com.rjdeleon.tourista.core.api.getNearbyPlaces(location, 1500,
                filter, "",
                "AIzaSyDjS_1Kjov8pm6mLwSiQV7ZY1E39XCQiag",
                object : GetNearbyPlacesListener {
                    override fun onReceive(results: List<Result>) {
                        mNearbyPlaces.value = results
                    }

                    override fun onError() {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }
                }
                )
    }
}