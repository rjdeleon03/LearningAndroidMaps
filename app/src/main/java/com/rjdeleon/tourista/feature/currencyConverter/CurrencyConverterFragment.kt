package com.rjdeleon.tourista.feature.currencyConverter


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.RotateAnimation
import androidx.lifecycle.ViewModelProviders
import com.mynameismidori.currencypicker.CurrencyPicker
import com.rjdeleon.tourista.Constants
import com.rjdeleon.tourista.core.base.BaseFragment

import com.rjdeleon.tourista.databinding.FragmentCurrencyConverterBinding
import kotlinx.android.synthetic.main.fragment_currency_converter.*

/**
 * A simple [Fragment] subclass.
 *
 */
class CurrencyConverterFragment : BaseFragment() {

    private lateinit var mViewModel: CurrencyConverterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(CurrencyConverterViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = FragmentCurrencyConverterBinding.inflate(inflater, container, false)
        binding.viewModel = mViewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currencySourceText.setOnClickListener {

            val picker = CurrencyPicker.newInstance("Select Currency")
            picker.setListener { _, code, _, flagDrawableResID ->
                picker.dismiss()
                mViewModel.updateSourceCurrency(code, flagDrawableResID)
            }
            picker.show(fragmentManager!!, Constants.CURRENCY_PICKER_FRAGMENT_KEY)
        }

        currencyDestText.setOnClickListener {

            val picker = CurrencyPicker.newInstance("Select Currency")
            picker.setListener { _, code, _, flagDrawableResID ->
                picker.dismiss()
                mViewModel.updateDestCurrency(code,flagDrawableResID)
            }
            picker.show(fragmentManager!!, Constants.CURRENCY_PICKER_FRAGMENT_KEY)
        }

        currencyConvertButton.setOnClickListener {
            hideKeyboard()
            mViewModel.convertCurrency()

        }

        currencySwitchButton.setOnClickListener {
            val rotation = currencySwitchButton.rotation + 180F
            currencySwitchButton.animate().rotation(rotation)
                    .interpolator = AccelerateDecelerateInterpolator()
            mViewModel.switchCurrencies()
        }


    }


}
