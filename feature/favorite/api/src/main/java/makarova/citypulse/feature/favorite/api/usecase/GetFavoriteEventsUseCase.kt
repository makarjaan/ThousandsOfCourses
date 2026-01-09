package makarova.citypulse.feature.favorite.api.usecase

import makarova.citypulse.feature.main.api.model.EventModel

interface GetFavoriteEventsUseCase {
    suspend operator fun invoke(userEmail: String): List<EventModel>
}