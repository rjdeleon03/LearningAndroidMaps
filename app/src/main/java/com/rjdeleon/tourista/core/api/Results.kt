package com.rjdeleon.tourista.core.api

import com.google.android.libraries.places.api.internal.impl.net.pablo.PlaceResult.OpeningHours
import com.google.android.libraries.places.api.internal.impl.net.pablo.PlaceResult.Geometry
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Result : Serializable {

    /**
     * @return The geometry
     */
    /**
     * @param geometry The geometry
     */
    @SerializedName("geometry")
    var geometry: Geometry? = null

    /**
     * @return The icon
     */
    /**
     * @param icon The icon
     */
    @SerializedName("icon")
    var icon: String? = null

    /**
     * @return The id
     */
    /**
     * @param id The id
     */
    @SerializedName("id")
    var id: String? = null

    /**
     * @return The name
     */
    /**
     * @param name The name
     */
    @SerializedName("name")
    var name: String? = null

    /**
     * @return The openingHours
     */
    /**
     * @param openingHours The opening_hours
     */
    @SerializedName("opening_hours")
    var openingHours: OpeningHours? = null

    /**
     * @return The photos
     */
    /**
     * @param photos The photos
     */
//    @SerializedName("photos")
//    var photos: List<Photo> = ArrayList<Photo>()

    /**
     * @return The placeId
     */
    /**
     * @param placeId The place_id
     */
    @SerializedName("place_id")
    var placeId: String? = null

    /**
     * @return The rating
     */
    /**
     * @param rating The rating
     */
    @SerializedName("rating")
    var rating: Double? = null

    /**
     * @return The reference
     */
    /**
     * @param reference The reference
     */
    @SerializedName("reference")
    var reference: String? = null

    /**
     * @return The scope
     */
    /**
     * @param scope The scope
     */
    @SerializedName("scope")
    var scope: String? = null

    /**
     * @return The types
     */
    /**
     * @param types The types
     */
    @SerializedName("types")
    var types: List<String> = ArrayList()

    /**
     * @return The vicinity
     */
    /**
     * @param vicinity The vicinity
     */
    @SerializedName("vicinity")
    var vicinity: String? = null

    /**
     * @return The priceLevel
     */
    /**
     * @param priceLevel The price_level
     */
    @SerializedName("price_level")
    var priceLevel: Int? = null

}