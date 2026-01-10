package makarova.citypulse.feature.main.api.usecase

import makarova.citypulse.feature.main.api.model.EventModel

interface GetEventByCategoryUseCase {
    suspend operator fun invoke(city: String, category: String, page: Int): List<EventModel>
}