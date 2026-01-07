package makarova.citypulse.feature.main.api.usecase

interface IncreaseCategoryScoreUseCase {
    suspend operator fun invoke(email: String, category: String)
}