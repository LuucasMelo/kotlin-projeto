import com.google.gson.annotations.SerializedName
import java.math.BigInteger

data class Variations (
		@SerializedName("id") val id : BigInteger,
		@SerializedName("price") val price : Double,
		@SerializedName("attribute_combinations") val attribute_combinations : List<Attribute_combinations>,
		@SerializedName("available_quantity") val available_quantity : Int,
		@SerializedName("sold_quantity") val sold_quantity : Int,
		@SerializedName("sale_terms") val sale_terms : List<String>,
		@SerializedName("picture_ids") val picture_ids : List<String>,
		@SerializedName("catalog_product_id") val catalog_product_id : String
)