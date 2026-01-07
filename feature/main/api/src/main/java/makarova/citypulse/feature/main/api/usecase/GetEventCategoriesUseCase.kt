package makarova.citypulse.feature.main.api.usecase

import makarova.citypulse.feature.main.api.model.EventCategoryModel

interface GetEventCategoriesUseCase {
    suspend operator fun invoke(): List<EventCategoryModel>
}
