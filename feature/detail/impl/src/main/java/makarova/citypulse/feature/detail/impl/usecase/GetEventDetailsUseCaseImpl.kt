package makarova.citypulse.feature.detail.impl.usecase

import makarova.citypulse.feature.detail.api.repository.EventDetailsRepository
import makarova.citypulse.feature.detail.api.usecase.GetEventDetailsUseCase
import makarova.citypulse.feature.main.api.model.EventModel
import javax.inject.Inject

class GetEventDetailsUseCaseImpl @Inject constructor(
    private val repository: EventDetailsRepository
) : GetEventDetailsUseCase {

    override suspend fun invoke(eventId: String): EventModel {
        return repository.getEventDetails(eventId)
    }
}
