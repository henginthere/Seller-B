package com.ssafy.sellerb.data.remote.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TokenUploadRequest(

    @Expose
    @SerializedName("customerToken")
    val token: String,

    @Expose
    @SerializedName("customerId")
    val id: String
)