package com.lucas.estudo.data.response.ProdutoDetalhe

import com.google.gson.annotations.SerializedName

data class Value_struct(
        @SerializedName("number") val number: Int,
        @SerializedName("unit") val unit: String
)