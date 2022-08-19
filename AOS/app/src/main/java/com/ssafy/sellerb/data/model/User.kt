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
    var seq: Long,

    @Expose
    @SerializedName("userName")
    var name: String?,

    @Expose
    @SerializedName("userEmail")
    var email: String?,

    @Expose
    @SerializedName("userBirth")
    var birth: String?,

    @Expose
    @SerializedName("userToken")
    var token: String?
    )