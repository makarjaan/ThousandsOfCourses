package makarova.citypulse.feature.profile.impl.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import makarova.citypulse.feature.main.api.repository.CategoriesRepository
import makarova.citypulse.feature.profile.api.usecase.GetTopCategoriesUseCase
import javax.inject.Inject

class GetTopCategoriesUseCaseImpl @Inject constructor(
    private val repository: CategoriesRepository,
    private val ioDispatcher: CoroutineDispatcher
) : GetTopCategoriesUseCase {

    override suspend operator fun invoke(email: String): List<String> {
        return withContext(ioDispatcher) {
            return@withContext repository.getTopCategories(email)
        }
    }
}
