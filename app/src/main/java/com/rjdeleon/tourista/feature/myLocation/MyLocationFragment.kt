package com.rjdeleon.tourista.feature.myLocation


import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle

import androidx.fragment.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.places.GeoDataClient
import com.google.android.gms.location.places.Places
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.location.LocationComponent
import com.mapbox.mapboxsdk.location.modes.CameraMode
import com.mapbox.mapboxsdk.location.modes.RenderMode
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style
import com.rjdeleon.tourista.Constants.*
import com.rjdeleon.tourista.R

import com.rjdeleon.tourista.databinding.FragmentMyLocationBinding
import kotlinx.android.synthetic.main.fragment_my_location.*


/**
 * A simple [Fragment] subclass.
 */
class MyLocationFragment : Fragment() {

    private lateinit var mViewModel : MyLocationViewModel
    private lateinit var mMapboxMap : MapboxMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = ViewModelProviders.of(this).get(MyLocationViewModel::class.java)

        Mapbox.getInstance(context!!, getString(R.string.api_key_mapbox))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val binding = FragmentMyLocationBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.viewModel = mViewModel
        binding.setLifecycleOwner(this)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {

            if (!it.isChecked) {
                when (it.itemId) {
                    R.id.menu_food ->
                        mViewModel.initNearbyPlacesRetrieval(FILTER_LOCATIONS_RESTAURANT)
                    R.id.menu_hotels ->
                        mViewModel.initNearbyPlacesRetrieval(FILTER_LOCATIONS_HOTEL)
                    R.id.menu_sights ->
                        mViewModel.initNearbyPlacesRetrieval(FILTER_LOCATIONS_ATTRACTION)
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
                getLastKnownLocation(it)
            }
        }
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
    }

    private fun getLocationPermission() {
        ActivityCompat.requestPermissions(activity!!,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSIONS_REQ_ACCESS_FINE_LOCATION)
    }

}// Required empty public constructor
