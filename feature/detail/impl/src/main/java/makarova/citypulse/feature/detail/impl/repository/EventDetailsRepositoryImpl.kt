package makarova.citypulse.feature.detail.impl.repository

import makarova.citypulse.feature.detail.api.repository.EventDetailsRepository
import makarova.citypulse.feature.main.api.model.EventModel
import makarova.citypulse.network.KudagoApi
import makarova.citypulse.network.mapper.KudagoResponseMapper
import javax.inject.Inject

class EventDetailsRepositoryImpl @Inject constructor(
    private val api: KudagoApi,
    private val mapper: KudagoResponseMapper
) : EventDetailsRepository {

    override suspend fun getEventDetails(eventId: String): EventModel {
        val response = api.getEventDetails(eventId = eventId)
        return mapper.mapEvent(response)
    }
}
