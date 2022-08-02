package com.ssafy.sellerb.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User (

    @Expose
    @SerializedName("userId")
    val id: String,

    @Expose
    @SerializedName("userName")
    val name: String
    )