package makarova.citypulse.feature.main.api.repository

import makarova.citypulse.feature.main.api.model.EventCategoryModel

interface CategoriesRepository {
    suspend fun getCategories(): List<EventCategoryModel>
}