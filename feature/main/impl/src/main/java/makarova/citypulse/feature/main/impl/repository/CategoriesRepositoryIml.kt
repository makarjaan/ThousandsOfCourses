package makarova.citypulse.feature.main.impl.repository

import makarova.citypulse.database.dao.CategoryInterestDao
import makarova.citypulse.feature.main.api.model.EventCategoryModel
import makarova.citypulse.feature.main.api.repository.CategoriesRepository
import makarova.citypulse.network.KudagoApi
import makarova.citypulse.network.mapper.KudagoResponseMapper

import javax.inject.Inject

class CategoriesRepositoryIml @Inject constructor(
    private val api: KudagoApi,
    private val mapper: KudagoResponseMapper,
    private val dao: CategoryInterestDao
): CategoriesRepository {

    override suspend fun getCategories(): List<EventCategoryModel> {
        return mapper.mapCategories(api.getCategories())
    }

    override suspend fun getUserCategoryScores(email: String): Map<String, Int> {
        return dao.getAllForUser(email)
            .associate { it.category to it.score }
    }

    override suspend fun getTopCategories(email: String): List<String> {
        return dao.getTopCategoriesForUser(email)
    }
}