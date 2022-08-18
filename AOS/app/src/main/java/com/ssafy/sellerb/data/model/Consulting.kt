package com.ssafy.sellerb.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Consulting(
    @Expose
    @SerializedName("consultingSeq")
    val consultingSeq: Long,

    @Expose
    @SerializedName("consultingState")
    val consultingState: String,

    @Expose
    @SerializedName("consultingStartDate")
    val consultingDate: String,

    @Expose
    @SerializedName("product")
    val product: Product,

    @Expose
    @SerializedName("consultant")
    val consultant: Consultant

) {
    data class Consultant(
        @Expose
        @SerializedName("consultantSeq")
        val consultantSeq: Long,

        @Expose
        @SerializedName("consultantName")
        val consultantName: String,

        @Expose
        @SerializedName("consultantImageUrl")
        val consultantThumbnail: String

    )
}
