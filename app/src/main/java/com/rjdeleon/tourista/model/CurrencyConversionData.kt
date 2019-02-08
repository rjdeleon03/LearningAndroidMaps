package com.rjdeleon.tourista.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.rjdeleon.tourista.BR

class CurrencyConversionData(private var _sourceCurrency : String,
                             private var _sourceAmount : Int,
                             private var _sourceFlagId : Int,
                             private var _destCurrency : String,
                             private var _destAmount : Int,
                             private var _destFlagId : Int) : BaseObservable() {

    var sourceCurrency : String
        @Bindable get() = _sourceCurrency
        set(value) {
            _sourceCurrency = value
            notifyPropertyChanged(BR.sourceCurrency)
        }

    var sourceAmount : Int
        @Bindable get() = _sourceAmount
        set(value) {
            _sourceAmount = value
            notifyPropertyChanged(BR.sourceAmount)
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

    var destAmount : Int
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
}