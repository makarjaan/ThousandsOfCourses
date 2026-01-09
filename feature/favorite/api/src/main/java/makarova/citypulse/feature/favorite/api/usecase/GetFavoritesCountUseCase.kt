package makarova.citypulse.feature.favorite.api.usecase

interface GetFavoritesCountUseCase {
    suspend operator fun invoke(userEmail: String): Int
}
