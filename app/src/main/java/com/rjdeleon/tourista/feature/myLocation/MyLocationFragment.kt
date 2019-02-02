package com.rjdeleon.tourista.feature.myLocation


import android.os.Bundle

import androidx.fragment.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.rjdeleon.tourista.R


/**
 * A simple [Fragment] subclass.
 */
class MyLocationFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_location, container, false)
    }

}// Required empty public constructor
