package makarova.thousandsofcourses.network.response

import com.google.gson.annotations.SerializedName

data class CoursesResponseItem (
    @SerializedName("id")
    val id: Long?,
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

data class CoursesResponse(
    @SerializedName("courses")
    val courses: List<CoursesResponseItem>
)