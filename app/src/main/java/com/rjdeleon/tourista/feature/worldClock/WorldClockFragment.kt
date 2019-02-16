package com.rjdeleon.tourista.feature.worldclock


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.rjdeleon.tourista.R

/**
 * A simple [Fragment] subclass.
 *
 */
class WorldClockFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_world_clock, container, false)
    }


}
