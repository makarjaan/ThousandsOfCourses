package makarova.citypulse.network

import makarova.citypulse.network.pojo.response.KudagoCategoryResponse
import makarova.citypulse.network.pojo.response.KudagoEventsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface KudagoApi {

    @GET("events/")
    suspend fun getEvents(
        @Query("location") location: String,
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = 20,
        @Query("fields") fields: String =
            "id,title,dates,place,images,location,categories,is_free,favorites_count",
        @Query("expand") expand: String = "place,images,location",
        @Query("text_format") textFormat: String = "plain",
        @Query("categories") category: String? = null
    ): KudagoEventsResponse?

    @GET("event-categories/")
    suspend fun getCategories(
        @Query("lang") lang: String = "ru",
        @Query("fields") fields: String = "slug,name",
        @Query("order_by") orderBy: String = "slug"
    ): List<KudagoCategoryResponse>?
}
