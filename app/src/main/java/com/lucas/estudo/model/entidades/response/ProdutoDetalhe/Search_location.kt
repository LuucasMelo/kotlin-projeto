import com.google.gson.annotations.SerializedName

data class Search_location (

		@SerializedName("neighborhood") val neighborhood : Neighborhood,
		@SerializedName("city") val city : City,
		@SerializedName("state") val state : State
)