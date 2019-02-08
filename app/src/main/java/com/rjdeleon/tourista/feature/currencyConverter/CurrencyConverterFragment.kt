package com.rjdeleon.tourista.feature.currencyConverter


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mynameismidori.currencypicker.CurrencyPicker
import com.rjdeleon.tourista.Constants

import com.rjdeleon.tourista.R
import kotlinx.android.synthetic.main.fragment_currency_converter.*

/**
 * A simple [Fragment] subclass.
 *
 */
class CurrencyConverterFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_currency_converter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currencySourceText.setOnClickListener {

            val picker = CurrencyPicker.newInstance("Select Currency")
            picker.setListener { name, code, symbol, flagDrawableResID ->
                currencySourceImage.setImageDrawable(context?.resources?.getDrawable(flagDrawableResID)!!)
                picker.dismiss();
            }
            picker.show(fragmentManager!!, Constants.CURRENCY_PICKER_FRAGMENT_KEY)
        }

        currencyDestText.setOnClickListener {

            val picker = CurrencyPicker.newInstance("Select Currency")
            picker.setListener { name, code, symbol, flagDrawableResID ->
                currencyDestImage.setImageDrawable(context?.resources?.getDrawable(flagDrawableResID)!!)
                picker.dismiss();
            }
            picker.show(fragmentManager!!, Constants.CURRENCY_PICKER_FRAGMENT_KEY)
        }
    }


}
