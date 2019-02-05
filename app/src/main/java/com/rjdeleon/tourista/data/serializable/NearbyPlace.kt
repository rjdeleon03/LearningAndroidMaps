package com.rjdeleon.tourista.data.serializable

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class NearbyPlace : Serializable {

    @SerializedName("lat")
    var latitude : Double? = null

    @SerializedName("lon")
    var longitude : Double? = null

    @SerializedName("tag_type")
    var tag : String? = null

    @SerializedName("name")
    var name : String? = null

    @SerializedName("distance")
    var distance : Int? = null
}