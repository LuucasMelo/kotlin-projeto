package com.lucas.estudo.data.response.Produto

import com.google.gson.annotations.SerializedName

data class ResponseProdutos(
        @SerializedName("site_id")
    val site_id : String,
        @SerializedName("seller")
    val seller : Seller,
        @SerializedName("paging")
    val paging : Paging,
        @SerializedName("results")
    val results : List<Results>,
)