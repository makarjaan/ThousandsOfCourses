package makarova.citypulse.network.pojo.response

import com.google.gson.annotations.SerializedName

class KudagoCategoryResponse(
    @SerializedName("slug")
    val slug: String,
    @SerializedName("name")
    val name: String
)