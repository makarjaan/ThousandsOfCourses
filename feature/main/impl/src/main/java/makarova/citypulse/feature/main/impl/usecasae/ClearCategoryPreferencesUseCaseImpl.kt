package makarova.citypulse.feature.main.impl.usecasae

import makarova.citypulse.feature.main.api.repository.RecommendationsRepository
import makarova.citypulse.feature.main.api.usecase.ClearCategoryPreferencesUseCase
import javax.inject.Inject

class ClearCategoryPreferencesUseCaseImpl @Inject constructor(
    private val repository: RecommendationsRepository
): ClearCategoryPreferencesUseCase {

    override suspend operator fun invoke(email: String) {
        repository.clearPreferences(email)
    }
}