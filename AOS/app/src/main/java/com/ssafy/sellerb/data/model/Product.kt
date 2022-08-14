package com.ssafy.sellerb.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Product(
    @Expose
    @SerializedName("productSeq")
    var seq: Long,

    @Expose
    @SerializedName("productGroupName")
    var productGroupName: String,

    @Expose
    @SerializedName("productGroupSeq")
    var productGroupSeq: Long,

    @Expose
    @SerializedName("productId")
    var productId: String,

    @Expose
    @SerializedName("productName")
    var name: String,

    @Expose
    @SerializedName("productPrice")
    var price: Int,

    @Expose
    @SerializedName("productManual")
    var manual: String,

    @Expose
    @SerializedName("productThumbnail")
    var thumbnail: String
)