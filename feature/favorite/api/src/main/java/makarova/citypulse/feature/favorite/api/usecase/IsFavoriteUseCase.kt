package makarova.citypulse.feature.favorite.api.usecase

interface IsFavoriteUseCase {
    suspend operator fun invoke(eventId: String, userEmail: String): Boolean
}