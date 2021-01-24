import com.google.gson.annotations.SerializedName

data class Seller_address (

		@SerializedName("city") val city : City,
		@SerializedName("state") val state : State,
		@SerializedName("country") val country : Country,
		@SerializedName("search_location") val search_location : Search_location,
		@SerializedName("id") val id : Int
)