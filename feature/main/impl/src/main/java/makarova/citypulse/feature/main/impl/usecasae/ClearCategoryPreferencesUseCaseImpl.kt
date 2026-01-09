package makarova.citypulse.feature.main.impl.usecasae

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import makarova.citypulse.feature.main.api.repository.RecommendationsRepository
import makarova.citypulse.feature.main.api.usecase.ClearCategoryPreferencesUseCase
import javax.inject.Inject

class ClearCategoryPreferencesUseCaseImpl @Inject constructor(
    private val repository: RecommendationsRepository,
    private val ioDispatcher: CoroutineDispatcher
): ClearCategoryPreferencesUseCase {

    override suspend operator fun invoke(email: String) {
        return withContext(ioDispatcher) {
            repository.clearPreferences(email)
        }
    }
}