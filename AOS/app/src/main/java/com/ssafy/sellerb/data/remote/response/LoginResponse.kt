package com.ssafy.sellerb.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.ssafy.sellerb.data.model.Token

data class LoginResponse (

    @Expose
    @SerializedName("statusCode")
    var statusCode: String,

    @Expose
    @SerializedName("status")
    var status: Int,

    @Expose
    @SerializedName("tokenDto")
    var token: Token,

    @Expose
    @SerializedName("authority")
    var authority: String,

    @Expose
    @SerializedName("seq")
    var seq: Long
)