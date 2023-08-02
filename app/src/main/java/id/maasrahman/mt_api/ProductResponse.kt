package id.maasrahman.mt_api


import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("limit")
    var limit: Int? = null,
    @SerializedName("products")
    var products: List<Product>? = null,
    @SerializedName("skip")
    var skip: Int? = null,
    @SerializedName("total")
    var total: Int? = null
)