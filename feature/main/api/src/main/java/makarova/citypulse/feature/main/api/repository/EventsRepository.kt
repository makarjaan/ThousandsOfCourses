package makarova.citypulse.feature.main.api.repository

import makarova.citypulse.feature.main.api.model.EventModel
import makarova.citypulse.feature.main.api.model.SearchResultModel

interface EventsRepository {

    suspend fun getEvents(city: String, ): List<EventModel>

    suspend fun getEventsByCategory(
        city: String,
        category: String,
        page: Int,
        pageSize: Int
    ): List<EventModel>

    suspend fun searchEvents(
        query: String,
        location: String = "msk",
        contentType: String? = null,
        page: Int = 1,
        pageSize: Int = 20,
        isFree: Boolean? = null
    ): List<SearchResultModel>

}
