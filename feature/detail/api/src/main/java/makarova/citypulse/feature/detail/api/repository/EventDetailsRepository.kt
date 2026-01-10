package makarova.citypulse.feature.detail.api.repository

import makarova.citypulse.feature.main.api.model.EventModel

interface EventDetailsRepository {
    suspend fun getEventDetails(eventId: String): EventModel
}
