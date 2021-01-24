package com.lucas.estudo.model.entidades.response.ProdutoDetalhe

import com.google.gson.annotations.SerializedName

data class Value_struct(
        @SerializedName("number") val number: Int,
        @SerializedName("unit") val unit: String
)