package com.ssafy.sellerb.data.remote.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SignupRequest(
    @Expose
    @SerializedName("customerId")
    private val id : String,

    @Expose
    @SerializedName("customerPass")
    private val pas : String,

    @Expose
    @SerializedName("customerName")
    private  val name : String,
)