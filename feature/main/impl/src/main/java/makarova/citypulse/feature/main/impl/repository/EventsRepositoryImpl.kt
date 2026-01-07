package makarova.citypulse.feature.main.impl.repository

import androidx.compose.foundation.pager.PageSize
import makarova.citypulse.feature.main.api.model.EventModel
import makarova.citypulse.feature.main.api.repository.EventsRepository
import makarova.citypulse.network.KudagoApi
import makarova.citypulse.network.mapper.KudagoResponseMapper
import javax.inject.Inject

class EventsRepositoryImpl @Inject constructor(
    private val kudagoApi: KudagoApi,
    private val kudagoMapper: KudagoResponseMapper,
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
}
