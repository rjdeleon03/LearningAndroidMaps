package com.rjdeleon.tourista.data.serializable

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PlaceResult : Serializable {

    @SerializedName("id")
    val id : String? = null

    @SerializedName("title")
    val name : String? = null

    @SerializedName("distance")
    val distance : Int? = null

    @SerializedName("vicinity")
    val vicinity : String? = null

    @SerializedName("position")
    val position : DoubleArray? = null
}

class PlaceResultWrapper : Serializable {

    @SerializedName("items")
    val items : List<PlaceResult>? = null
}

class PlaceResponse : Serializable {

    @SerializedName("results")
    val result : PlaceResultWrapper? = null
}