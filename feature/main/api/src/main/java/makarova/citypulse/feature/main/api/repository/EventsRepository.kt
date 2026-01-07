package makarova.citypulse.feature.main.api.repository

import makarova.citypulse.feature.main.api.model.EventModel

interface EventsRepository {

    suspend fun getEvents(
        city: String,
    ): List<EventModel>

    suspend fun getEventsByCategory(
        city: String,
        category: String,
        page: Int,
        pageSize: Int
    ): List<EventModel>

}
