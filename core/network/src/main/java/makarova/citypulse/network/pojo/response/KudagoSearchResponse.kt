package makarova.citypulse.network.pojo.response

import com.google.gson.annotations.SerializedName

class KudagoSearchResponse(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: List<KudagoSearchItemResponse>?
)

class KudagoSearchItemResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("ctype")
    val contentType: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("body_text")
    val bodyText: String?,
    @SerializedName("site_url")
    val siteUrl: String?,
    @SerializedName("images")
    val images: List<KudagoImageResponse>?,
    @SerializedName("tags")
    val tags: List<String>?,
    @SerializedName("categories")
    val categories: List<String>?,
    @SerializedName("location")
    val location: KudagoLocationResponse?,
    @SerializedName("dates")
    val dates: List<KudagoDateResponse>?,
    @SerializedName("place")
    val place: KudagoPlaceResponse?,
    @SerializedName("is_free")
    val isFree: Boolean?,
    @SerializedName("favorites_count")
    val favoritesCount: Int?,

)