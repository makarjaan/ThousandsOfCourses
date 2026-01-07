package makarova.citypulse.network.pojo.response

import com.google.gson.annotations.SerializedName

class KudagoEventsResponse(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: List<KudagoEventResponse>?
)

class KudagoEventResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("short_title")
    val shortTitle: String?,
    @SerializedName("dates")
    val dates: List<KudagoDateResponse>?,
    @SerializedName("place")
    val place: KudagoPlaceResponse?,
    @SerializedName("images")
    val images: List<KudagoImageResponse>?,
    @SerializedName("location")
    val location: KudagoLocationResponse?,
    @SerializedName("categories")
    val categories: List<String>?,
    @SerializedName("is_free")
    val isFree: Boolean?,
    @SerializedName("favorites_count")
    val favoritesCount: Int?
)

class KudagoDateResponse(
    @SerializedName("start")
    val start: Long?,
    @SerializedName("end")
    val end: Long?
)

class KudagoPlaceResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("address")
    val address: String?
)

class KudagoImageResponse(
    @SerializedName("image")
    val image: String?
)

class KudagoLocationResponse(
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("name")
    val name: String?
)


