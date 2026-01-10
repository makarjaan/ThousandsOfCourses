package makarova.citypulse.feature.main.impl.repository

import makarova.citypulse.feature.main.api.model.EventModel
import makarova.citypulse.feature.main.api.model.SearchResultModel
import makarova.citypulse.feature.main.api.repository.EventsRepository
import makarova.citypulse.network.KudagoApi
import makarova.citypulse.network.mapper.KudagoResponseMapper
import javax.inject.Inject

class EventsRepositoryImpl @Inject constructor(
    private val kudagoApi: KudagoApi,
    private val kudagoMapper: KudagoResponseMapper,
    private val apiService: KudagoApi,
    private val mapper: KudagoResponseMapper
): EventsRepository {

    override suspend fun getEvents(
        city: String,
    ): List<EventModel> {
        val response = kudagoApi.getEvents(
            location = city,
            page = 1,
            pageSize = 50,
            fields = "id,title,dates,place,images,location,categories,is_free,favorites_count",
            expand = "place,images,location",
            textFormat = "plain"
        )

        return kudagoMapper.mapEventsResponse(response)
    }

    override suspend fun getEventsByCategory(
        city: String,
        category: String,
        page: Int,
        pageSize: Int
    ): List<EventModel> {
        val response = kudagoApi.getEvents(
            location = city,
            page = page,
            pageSize = pageSize,
            fields = "id,title,dates,place,images,location,categories,is_free,favorites_count",
            expand = "place,images,location",
            textFormat = "plain",
            category = category
        )
        return kudagoMapper.mapEventsResponse(response)
    }

    override suspend fun searchEvents(
        query: String,
        location: String,
        contentType: String?,
        page: Int,
        pageSize: Int,
        isFree: Boolean?
    ): List<SearchResultModel> {
        return try {
            val response = apiService.search(
                query = query,
                location = location,
                contentType = contentType,
                page = page,
                pageSize = pageSize,
                isFree = if (isFree == true) 1 else null
            )

            mapper.mapSearchResponse(response)

        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}
