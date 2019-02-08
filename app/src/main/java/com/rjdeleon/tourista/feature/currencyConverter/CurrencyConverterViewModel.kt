package com.rjdeleon.tourista.feature.currencyConverter

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.rjdeleon.tourista.core.api.forex.GetCurrencyConversionListener
import com.rjdeleon.tourista.core.api.forex.getCurrencyConversion
import com.rjdeleon.tourista.model.CurrencyConversionData

class CurrencyConverterViewModel(application: Application) : AndroidViewModel(application) {

    private val mConversionData : MutableLiveData<CurrencyConversionData> = MutableLiveData()

    init {
        mConversionData.value = CurrencyConversionData(
                "PHP", 0.0, 0,
                "USD", 0.0, 0)
    }

    fun getConversionData() : MutableLiveData<CurrencyConversionData> {
        return mConversionData
    }

    fun updateSourceCurrency(currency : String, currencyFlagId : Int) {
        mConversionData.value?.sourceCurrency = currency
        mConversionData.value?.sourceFlagId = currencyFlagId
    }

    fun updateDestCurrency(currency : String, currencyFlagId : Int) {
        mConversionData.value?.destCurrency = currency
        mConversionData.value?.destFlagId = currencyFlagId
    }

    fun convertCurrency() {
        val data = mConversionData.value ?: return

        getCurrencyConversion(data.sourceCurrency, data.destCurrency,
                object : GetCurrencyConversionListener {
                    override fun onReceive(rate: Double) {
                        data.destAmount = data.sourceAmount * rate
                    }

                    override fun onError() {

                    }
                })
    }

    fun switchCurrencies() {
        mConversionData.value?.switchCurrencies()
    }
}