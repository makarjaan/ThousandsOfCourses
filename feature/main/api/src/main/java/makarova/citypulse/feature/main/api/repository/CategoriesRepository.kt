package makarova.citypulse.feature.main.api.repository

import makarova.citypulse.feature.main.api.model.EventCategoryModel

interface CategoriesRepository {
    suspend fun getCategories(): List<EventCategoryModel>
    suspend fun getUserCategoryScores(email: String): Map<String, Int>
    suspend fun getTopCategories(email: String): List<String>
}