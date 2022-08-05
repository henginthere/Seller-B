package com.ssafy.sellerb.data.remote.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginRequest (

    @Expose
    @SerializedName("id")
    private val id : String,

    @Expose
    @SerializedName("pass")
    private val pas : String

)