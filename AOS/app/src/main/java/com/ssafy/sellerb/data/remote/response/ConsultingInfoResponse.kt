package com.ssafy.sellerb.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ConsultingInfoResponse (
    @Expose
    @SerializedName("consultingSeq")
    val consultingSeq: Long,

    @Expose
    @SerializedName("consultingState")
    val consultingState: String

    )