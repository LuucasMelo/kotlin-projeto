import com.google.gson.annotations.SerializedName


data class Attribute_combinations (

		@SerializedName("id") val id : String,
		@SerializedName("name") val name : String,
		@SerializedName("value_id") val value_id : Int,
		@SerializedName("value_name") val value_name : String,
		@SerializedName("value_struct") val value_struct : String,
		@SerializedName("values") val values : List<Values>
)