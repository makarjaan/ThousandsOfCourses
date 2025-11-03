package makarova.thousandsofcourses.network.response

import com.google.gson.annotations.SerializedName

class CoursesResponse (
    @SerializedName("id")
    val id: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("text")
    val text: String?,
    @SerializedName("price")
    val price: String?,
    @SerializedName("rate")
    val rate: Double?,
    @SerializedName("startDate")
    val startDate: String?,
    @SerializedName("hasLike")
    val hasLike: Boolean?,
    @SerializedName("publishDate")
    val publishDate: String?
)