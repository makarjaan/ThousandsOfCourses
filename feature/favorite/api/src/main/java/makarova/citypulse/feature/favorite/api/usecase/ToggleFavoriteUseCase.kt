package makarova.citypulse.feature.favorite.api.usecase

import makarova.citypulse.feature.main.api.model.EventModel

interface ToggleFavoriteUseCase {
    suspend operator fun invoke(event: EventModel, userEmail: String): Boolean
}