package com.lucas.estudo.model.entidades.response.Produto

import com.google.gson.annotations.SerializedName
import com.lucas.estudo.model.entidades.Produto

data class Results (

        @SerializedName("id")
	val id : String,
        @SerializedName("site_id")
	val site_id : String,
        @SerializedName("title")
	val title : String,
        @SerializedName("seller")
	val seller : Seller,
        @SerializedName("price")
	val price : Double,
        @SerializedName("prices")
	val prices : Prices,
        @SerializedName("available_quantity")
	val available_quantity : Int,
        @SerializedName("sold_quantity")
	val sold_quantity : Int,
        @SerializedName("thumbnail")
	val thumbnail : String,
        @SerializedName("installments")
	val installments : Installments,
){
    fun toProduto(): Produto {
        return Produto(id = id, nome = title, preco = price, thumbnail = thumbnail, descricaoId = "")
    }
}