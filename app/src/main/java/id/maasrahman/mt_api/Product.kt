package id.maasrahman.mt_api


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    @SerializedName("brand")
    var brand: String? = null,
    @SerializedName("category")
    var category: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("discountPercentage")
    var discountPercentage: Double? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("images")
    var images: List<String?>? = null,
    @SerializedName("price")
    var price: Int? = null,
    @SerializedName("rating")
    var rating: Double? = null,
    @SerializedName("stock")
    var stock: Int? = null,
    @SerializedName("thumbnail")
    var thumbnail: String? = null,
    @SerializedName("title")
    var title: String? = null
): Parcelable