package com.ssafy.sellerb.data.remote.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WaitingRequest (
    @Expose
    @SerializedName("customerSeq")
    val customerSeq: Long,

    @Expose
    @SerializedName("productSeq")
    val productSeq: Long,

    @Expose
    @SerializedName("waitingCustomerState")
    val waitingCustomerState: Boolean

    )