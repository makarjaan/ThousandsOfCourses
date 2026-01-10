package makarova.citypulse.feature.detail.api.usecase

import makarova.citypulse.feature.main.api.model.EventModel

interface GetEventDetailsUseCase {
    suspend operator fun invoke(eventId: String): EventModel
}
