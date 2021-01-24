import com.google.gson.annotations.SerializedName
import com.lucas.estudo.model.entidades.Produto

data class ResponseProdutoDetalhe(

		@SerializedName("id")
		val id : String,
		@SerializedName("site_id")
		val site_id : String,
		@SerializedName("title")
		val title : String,
		@SerializedName("price")
		val price : Double,
		@SerializedName("base_price")
		val base_price : Double,
		@SerializedName("original_price")
		val original_price : String,
		@SerializedName("currency_id")
		val currency_id : String,
		@SerializedName("initial_quantity")
		val initial_quantity : Int,
		@SerializedName("available_quantity")
		val available_quantity : Int,
		@SerializedName("sold_quantity")
		val sold_quantity : Int,
		@SerializedName("pictures")
		val pictures : List<Pictures>,
		@SerializedName("descriptions")
		val descriptions : List<Descriptions>,
		@SerializedName("international_delivery_mode")
		val international_delivery_mode : String,
		@SerializedName("seller_address")
		val seller_address : Seller_address,
		@SerializedName("seller_contact")
		val seller_contact : String,
		@SerializedName("coverage_areas")
		val coverage_areas : List<String>,
		@SerializedName("warnings")
		val warnings : List<String>,
		@SerializedName("listing_source")
		val listing_source : String,
		@SerializedName("status")
		val status : String,
		@SerializedName("sub_status")
		val sub_status : List<String>,
		@SerializedName("tags")
		val tags : List<String>,
		@SerializedName("warranty")
		val warranty : String,
		@SerializedName("catalog_product_id")
		val catalog_product_id : String,
		@SerializedName("domain_id")
		val domain_id : String,
		@SerializedName("parent_item_id")
		val parent_item_id : String,
		@SerializedName("differential_pricing")
		val differential_pricing : String,
		@SerializedName("deal_ids")
		val deal_ids : List<String>,
		@SerializedName("automatic_relist")
		val automatic_relist : Boolean,
		@SerializedName("date_created")
		val date_created : String,
		@SerializedName("last_updated")
		val last_updated : String,
		@SerializedName("health")
		val health : Double,
		@SerializedName("catalog_listing")
		val catalog_listing : Boolean,
		@SerializedName("secure_thumbnail")
		val secure_thumbnail : String
){

	fun toProduto(): Produto {
		return Produto(id = id, nome = title, preco = price, thumbnail = secure_thumbnail, fotos = pictures.map { it.secure_url }, descricaoId = descriptions.map { it.id }.first())
	}

}