package makarova.citypulse.feature.main.impl.usecasae

import makarova.citypulse.feature.main.api.repository.RecommendationsRepository
import makarova.citypulse.feature.main.api.usecase.IncreaseCategoryScoreUseCase
import javax.inject.Inject

class IncreaseCategoryScoreUseCaseImpl @Inject constructor(
    private val repository: RecommendationsRepository
): IncreaseCategoryScoreUseCase {

    override suspend operator fun invoke(email: String, category: String) {
        repository.increaseCategoryScore(email, category)
    }
}