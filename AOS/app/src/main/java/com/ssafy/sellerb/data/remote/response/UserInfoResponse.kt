package com.ssafy.sellerb.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserInfoResponse (
    @Expose
    @SerializedName("customerName")
    var name: String?,

    @Expose
    @SerializedName("customerEmail")
    var email: String?,

    @Expose
    @SerializedName("customerBirth")
    var birth: String?,

    @Expose
    @SerializedName("customerToken")
    var token: String?
        )