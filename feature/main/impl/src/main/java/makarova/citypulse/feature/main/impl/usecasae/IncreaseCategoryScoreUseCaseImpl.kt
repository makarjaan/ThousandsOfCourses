package makarova.citypulse.feature.main.impl.usecasae

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import makarova.citypulse.feature.main.api.repository.RecommendationsRepository
import makarova.citypulse.feature.main.api.usecase.IncreaseCategoryScoreUseCase
import javax.inject.Inject

class IncreaseCategoryScoreUseCaseImpl @Inject constructor(
    private val repository: RecommendationsRepository,
    private val ioDispatcher: CoroutineDispatcher
): IncreaseCategoryScoreUseCase {

    override suspend operator fun invoke(email: String, category: String) {
        return withContext(ioDispatcher) {
            repository.increaseCategoryScore(email, category)
        }
    }
}