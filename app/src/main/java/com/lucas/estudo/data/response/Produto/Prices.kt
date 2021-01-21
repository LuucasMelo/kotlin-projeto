package com.lucas.estudo.data.response.Produto

import com.google.gson.annotations.SerializedName

data class Prices (

        @SerializedName("id")
	val id : String,
        @SerializedName("prices")
	val prices : List<Prices>,
        @SerializedName("presentation")
	val presentation : Presentation,
        @SerializedName("payment_method_prices")
	val payment_method_prices : List<String>
)