package makarova.citypulse.feature.main.api.usecase

import makarova.citypulse.feature.main.api.model.EventModel

interface GetRecommendedEventsUseCase {
    suspend operator fun invoke(city: String): List<EventModel>
}