package com.ssafy.sellerb.data.remote.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ConsultingStartRequest(
    @Expose
    @SerializedName("consultingState")
    val consultingState: String
)