package com.rjdeleon.tourista.feature.currencyConverter


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.mynameismidori.currencypicker.CurrencyPicker
import com.rjdeleon.tourista.Constants

import com.rjdeleon.tourista.databinding.FragmentCurrencyConverterBinding
import kotlinx.android.synthetic.main.fragment_currency_converter.*

/**
 * A simple [Fragment] subclass.
 *
 */
class CurrencyConverterFragment : Fragment() {

    private lateinit var mViewModel: CurrencyConverterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(CurrencyConverterViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var binding = FragmentCurrencyConverterBinding.inflate(inflater, container, false)
        binding.viewModel = mViewModel
        binding.setLifecycleOwner(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currencySourceText.setOnClickListener {

            val picker = CurrencyPicker.newInstance("Select Currency")
            picker.setListener { name, code, symbol, flagDrawableResID ->
                picker.dismiss()
                mViewModel.updateSourceCurrency(code, flagDrawableResID)
            }
            picker.show(fragmentManager!!, Constants.CURRENCY_PICKER_FRAGMENT_KEY)
        }

        currencyDestText.setOnClickListener {

            val picker = CurrencyPicker.newInstance("Select Currency")
            picker.setListener { name, code, symbol, flagDrawableResID ->
                picker.dismiss()
                mViewModel.updateDestCurrency(code,flagDrawableResID)
            }
            picker.show(fragmentManager!!, Constants.CURRENCY_PICKER_FRAGMENT_KEY)
        }
    }


}
