package makarova.citypulse.feature.main.api.usecase

interface ClearCategoryPreferencesUseCase {
    suspend operator fun invoke(email: String)
}