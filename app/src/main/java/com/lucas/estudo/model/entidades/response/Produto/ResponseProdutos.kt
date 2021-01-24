package com.lucas.estudo.model.entidades.response.Produto

import com.google.gson.annotations.SerializedName
import com.lucas.estudo.model.entidades.Produto

data class ResponseProdutos(
        @SerializedName("site_id")
    val site_id : String,
        @SerializedName("seller")
    val seller : Seller,
        @SerializedName("paging")
    val paging : Paging,
        @SerializedName("results")
    val results : List<Results>
){

    fun toListOfProdutos(): List<Produto>{
        return results.map { it.toProduto() }
    }

}