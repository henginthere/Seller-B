package com.ssafy.sellerb.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Waiting (
    @Expose
    @SerializedName("waitingCustomerSeq")
    val waitingCustomerSeq:Long,

    @Expose
    @SerializedName("productSeq")
    val productSeq: Long,

    @Expose
    @SerializedName("productThumbnail")
    val productThumbnail: String,

    @Expose
    @SerializedName("productName")
    val productName: String,

    @Expose
    @SerializedName("productId")
    val productId: String,

    @Expose
    @SerializedName("waitingCustomerState")
    val waitingCustomerState: Boolean
)