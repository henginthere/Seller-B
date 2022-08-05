package com.ssafy.sellerb.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Token(

    @Expose
    @SerializedName("accessToken")
    val accessToken: String,

    @Expose
    @SerializedName("refreshToken")
    val refreshToken: String,
)