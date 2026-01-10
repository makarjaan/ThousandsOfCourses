package makarova.citypulse.feature.main.impl.usecasae

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import makarova.citypulse.feature.main.api.model.EventCategoryModel
import makarova.citypulse.feature.main.api.repository.CategoriesRepository
import makarova.citypulse.feature.main.api.usecase.GetEventCategoriesUseCase
import javax.inject.Inject

class GetEventCategoriesUseCaseImpl @Inject constructor(
    private val repository: CategoriesRepository,
    private val ioDispatcher: CoroutineDispatcher
) : GetEventCategoriesUseCase {

    override suspend fun invoke(): List<EventCategoryModel> {
        return withContext(ioDispatcher) {
            return@withContext repository.getCategories()
        }
    }
}
