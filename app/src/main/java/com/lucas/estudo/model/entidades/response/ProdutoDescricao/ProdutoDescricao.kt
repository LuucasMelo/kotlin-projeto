import com.google.gson.annotations.SerializedName

data class ProdutoDescricao (
		@SerializedName("id") val id : String,
		@SerializedName("created") val created : String,
		@SerializedName("text") val text : String,
		@SerializedName("plain_text") val plain_text : String,
		@SerializedName("snapshot") val snapshot : Snapshot
)