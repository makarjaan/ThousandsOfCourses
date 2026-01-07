package makarova.citypulse.feature.main.impl.usecasae

import makarova.citypulse.feature.main.api.model.EventCategoryModel
import makarova.citypulse.feature.main.api.repository.CategoriesRepository
import makarova.citypulse.feature.main.api.usecase.GetEventCategoriesUseCase
import javax.inject.Inject

class GetEventCategoriesUseCaseImpl @Inject constructor(
    private val repository: CategoriesRepository
) : GetEventCategoriesUseCase {

    override suspend fun invoke(): List<EventCategoryModel> {
        return repository.getCategories()
    }
}
