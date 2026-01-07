package makarova.citypulse.feature.main.impl.usecasae

import android.util.Log
import makarova.citypulse.feature.main.api.model.EventModel
import makarova.citypulse.feature.main.api.repository.EventsRepository
import makarova.citypulse.feature.main.api.usecase.GetEventByCategoryUseCase
import javax.inject.Inject

class GetEventByCategoryUseCaseImpl @Inject constructor(
    private val eventsRepository: EventsRepository,
): GetEventByCategoryUseCase {
    override suspend fun invoke(
        city: String,
        category: String,
        page: Int
    ): List<EventModel> {
        val result= eventsRepository.getEventsByCategory(
            city = city,
            category = category,
            page = page,
            pageSize = 10
        )
        return result
    }
}