import com.google.gson.annotations.SerializedName

data class Neighborhood (

		@SerializedName("id") val id : String,
		@SerializedName("name") val name : String
)