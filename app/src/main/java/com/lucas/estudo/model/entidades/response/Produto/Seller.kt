package com.lucas.estudo.model.entidades.response.Produto

import com.google.gson.annotations.SerializedName

data class Seller (

	@SerializedName("id")
	val id : Int,
	@SerializedName("permalink")
	val permalink : String,
	@SerializedName("registration_date")
	val registration_date : String,
	@SerializedName("car_dealer")
	val car_dealer : Boolean,
	@SerializedName("real_estate_agency")
	val real_estate_agency : Boolean,
	@SerializedName("tags")
	val tags : List<String>,
	@SerializedName("nickname")
	val nickname : String
)