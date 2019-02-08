package com.rjdeleon.tourista.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.rjdeleon.tourista.BR

class CurrencyConversionData(private var _sourceCurrency : String,
                             private var _sourceAmount : Double,
                             private var _sourceFlagId : Int,
                             private var _destCurrency : String,
                             private var _destAmount : Double,
                             private var _destFlagId : Int) : BaseObservable() {

    var sourceCurrency : String
        @Bindable get() = _sourceCurrency
        set(value) {
            _sourceCurrency = value
            notifyPropertyChanged(BR.sourceCurrency)
        }

    var sourceAmount : Double
        @Bindable get() = _sourceAmount
        set(value) {
            _sourceAmount = value
        }

    var sourceFlagId : Int
        @Bindable get() = _sourceFlagId
        set(value) {
            _sourceFlagId = value
            notifyPropertyChanged(BR.sourceFlagId)
        }

    var destCurrency : String
        @Bindable get() = _destCurrency
        set(value) {
            _destCurrency = value
            notifyPropertyChanged(BR.destCurrency)
        }

    var destAmount : Double
        @Bindable get() = _destAmount
        set(value) {
            _destAmount = value
            notifyPropertyChanged(BR.destAmount)
        }

    var destFlagId : Int
        @Bindable get() = _destFlagId
        set(value) {
            _destFlagId = value
            notifyPropertyChanged(BR.destFlagId)
        }
    
    fun switchCurrencies() {
        _sourceAmount = 0.0
        _destAmount = 0.0

        val tempSourceCurrency = _sourceCurrency
        _sourceCurrency = _destCurrency
        _destCurrency = tempSourceCurrency

        val tempSourceFlagId = _sourceFlagId
        _sourceFlagId = _destFlagId
        _destFlagId = tempSourceFlagId

        notifyChange()
    }
}