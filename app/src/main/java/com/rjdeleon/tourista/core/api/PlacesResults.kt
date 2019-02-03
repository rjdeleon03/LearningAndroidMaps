package com.rjdeleon.tourista.core.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class PlacesResults : Serializable {

    @SerializedName("html_attributions")
    var htmlAttributions: List<Any> = ArrayList()

    @SerializedName("next_page_token")
    var nextPageToken: String? = null

    @SerializedName("results")
    var results: List<Result> = ArrayList<Result>()

    @SerializedName("status")
    var status: String? = null

}
