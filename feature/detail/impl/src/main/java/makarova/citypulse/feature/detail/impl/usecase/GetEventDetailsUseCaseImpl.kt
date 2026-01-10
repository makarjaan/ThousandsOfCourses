package makarova.citypulse.feature.detail.impl.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import makarova.citypulse.feature.detail.api.repository.EventDetailsRepository
import makarova.citypulse.feature.detail.api.usecase.GetEventDetailsUseCase
import makarova.citypulse.feature.main.api.model.EventModel
import javax.inject.Inject

class GetEventDetailsUseCaseImpl @Inject constructor(
    private val repository: EventDetailsRepository,
    private val ioDispatcher: CoroutineDispatcher
) : GetEventDetailsUseCase {

    override suspend fun invoke(eventId: String): EventModel {
        return withContext(ioDispatcher) {
            return@withContext repository.getEventDetails(eventId)
        }
    }
}
