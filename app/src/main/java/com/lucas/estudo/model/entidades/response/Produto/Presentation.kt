package com.lucas.estudo.model.entidades.response.Produto

import com.google.gson.annotations.SerializedName

data class Presentation (
	@SerializedName("display_currency")
	val display_currency : String
)