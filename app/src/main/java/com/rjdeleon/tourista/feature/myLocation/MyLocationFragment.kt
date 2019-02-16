package com.rjdeleon.tourista.feature.mylocation


import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle

import androidx.fragment.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mapbox.android.core.location.LocationEngine
import com.mapbox.android.core.location.LocationEngineCallback
import com.mapbox.android.core.location.LocationEngineRequest
import com.mapbox.android.core.location.LocationEngineResult
import com.mapbox.geojson.Point
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.location.modes.CameraMode
import com.mapbox.mapboxsdk.location.modes.RenderMode
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.plugins.annotation.Symbol
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions
import com.rjdeleon.tourista.Constants
import com.rjdeleon.tourista.Constants.*
import com.rjdeleon.tourista.R

import com.rjdeleon.tourista.databinding.FragmentMyLocationBinding
import kotlinx.android.synthetic.main.fragment_my_location.*
import java.lang.Exception


/**
 * A simple [Fragment] subclass.
 */
class MyLocationFragment : Fragment() {

    private lateinit var mViewModel : MyLocationViewModel
    private lateinit var mMapboxMap : MapboxMap
    private var mSymbolManager : SymbolManager? = null
    private var mSymbolList : List<Symbol> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = ViewModelProviders.of(this).get(MyLocationViewModel::class.java)

        Mapbox.getInstance(context!!, Constants.API_KEY_MAPBOX)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val binding = FragmentMyLocationBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.viewModel = mViewModel
        binding.lifecycleOwner = this

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {

            if (!it.isChecked) {
                when (it.itemId) {
                    R.id.menu_food ->
                        mViewModel.initNearbyPlacesRetrieval(FILTER_LOCATIONS_RESTAURANT)
                    R.id.menu_hotels ->
                        mViewModel.initNearbyPlacesRetrieval(FILTER_LOCATIONS_HOTEL)
                    R.id.menu_sights ->
                        mViewModel.initNearbyPlacesRetrieval(FILTER_LOCATIONS_ATTRACTION)
                    R.id.menu_atm ->
                        mViewModel.initNearbyPlacesRetrieval(FILTER_LOCATIONS_ATM)
                    R.id.menu_shopping ->
                        mViewModel.initNearbyPlacesRetrieval(FILTER_LOCATIONS_SHOPPING)
                }
            }
            true
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* Setup Google maps */
        setupGoogleMaps(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView?.onSaveInstanceState(outState)
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapView.onDestroy()
    }

    private fun setupGoogleMaps(savedInstanceState: Bundle?) {
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync { mapboxMap ->
            mMapboxMap = mapboxMap
            mapboxMap.setStyle(Style.MAPBOX_STREETS) {
                mSymbolManager = SymbolManager(mapView, mapboxMap, it)
                mSymbolManager?.iconAllowOverlap = true
                getLastKnownLocation(it)
            }
        }

        mViewModel.getNearbyPlaces().observe(this, Observer { results ->

            if (mSymbolManager == null) return@Observer
            if (!mSymbolList.isEmpty())
                mSymbolManager?.delete(mSymbolList)

            val options = ArrayList<SymbolOptions>()
            for (place in results) {

                if (place.position != null && place.position.size == 2) {
                    options.add(SymbolOptions()
                            .withGeometry(Point.fromLngLat(place.position[1], place.position[0])))
                }
            }
            mSymbolList = mSymbolManager!!.create(options)
        })
    }

    private fun getLastKnownLocation(loadedMapStyle : Style) {

        if(ActivityCompat.checkSelfPermission(context!!, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            getLocationPermission()
            return
        }

        val loc = mMapboxMap.locationComponent;
        loc.activateLocationComponent(context!!, loadedMapStyle)
        loc.isLocationComponentEnabled = true
        loc.cameraMode = CameraMode.TRACKING
        loc.renderMode = RenderMode.COMPASS
        requestLocation(loc.locationEngine!!)
    }

    private fun getLocationPermission() {
        ActivityCompat.requestPermissions(activity!!,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSIONS_REQ_ACCESS_FINE_LOCATION)
    }

    @SuppressLint("MissingPermission")
    private fun requestLocation(engine : LocationEngine) {

        engine.requestLocationUpdates(LocationEngineRequest.Builder(300000).build(), object : LocationEngineCallback<LocationEngineResult> {
            override fun onSuccess(result: LocationEngineResult?) {
                val lastLocation = result?.lastLocation
                if (lastLocation != null)
                    mViewModel.setPlacePoint(lastLocation.latitude, lastLocation.longitude)
            }

            override fun onFailure(exception: Exception) {
            }

        }, null)
    }

}// Required empty public constructor
