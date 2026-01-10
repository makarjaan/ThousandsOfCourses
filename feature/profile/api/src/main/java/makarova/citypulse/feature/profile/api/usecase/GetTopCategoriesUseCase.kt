package makarova.citypulse.feature.profile.api.usecase

interface GetTopCategoriesUseCase {
    suspend operator fun invoke(email: String): List<String>
}
