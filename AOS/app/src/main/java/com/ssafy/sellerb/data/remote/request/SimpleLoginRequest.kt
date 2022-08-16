package com.ssafy.sellerb.data.remote.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SimpleLoginRequest (

    @Expose
    @SerializedName("customerId")
    val id : String,

    @Expose
    @SerializedName("customerPass")
    private val pas : String,

    @Expose
    @SerializedName("customerName")
    private val name: String
)