package makarova.citypulse.feature.main.impl.repository

import makarova.citypulse.feature.main.api.model.EventCategoryModel
import makarova.citypulse.feature.main.api.repository.CategoriesRepository
import makarova.citypulse.network.KudagoApi
import makarova.citypulse.network.mapper.KudagoResponseMapper

import javax.inject.Inject

class CategoriesRepositoryIml @Inject constructor(
    private val api: KudagoApi,
    private val mapper: KudagoResponseMapper
): CategoriesRepository {

    override suspend fun getCategories(): List<EventCategoryModel> {
        return mapper.mapCategories(api.getCategories())
    }
}