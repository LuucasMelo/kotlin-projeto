import com.google.gson.annotations.SerializedName

data class Snapshot (

		@SerializedName("url") val url : String,
		@SerializedName("width") val width : Int,
		@SerializedName("height") val height : Int,
		@SerializedName("status") val status : String
)