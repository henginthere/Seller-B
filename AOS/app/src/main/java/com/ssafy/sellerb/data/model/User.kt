package com.ssafy.sellerb.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User (

    @Expose
    @SerializedName("userId")
    val id: String,

    @Expose
    @SerializedName("accessToken")
    var accessToken: String,

    @Expose
    @SerializedName("refreshToken")
    var refreshToken: String,

    @Expose
    @SerializedName("authority")
    var authority: String,

    @Expose
    @SerializedName("seq")
    var seq: Long

    )