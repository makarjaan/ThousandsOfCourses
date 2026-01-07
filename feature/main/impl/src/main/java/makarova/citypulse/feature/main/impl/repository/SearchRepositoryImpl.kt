package makarova.citypulse.feature.main.impl.repository

import makarova.citypulse.feature.main.api.model.SearchResultModel
import makarova.citypulse.feature.main.api.repository.SearchRepository
import makarova.citypulse.network.KudagoApi
import makarova.citypulse.network.mapper.KudagoResponseMapper
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val apiService: KudagoApi,
    private val mapper: KudagoResponseMapper
) : SearchRepository {

    override suspend fun searchEvents(
        query: String,
        location: String,
        contentType: String?,
        page: Int,
        pageSize: Int,
        isFree: Boolean?
    ): List<SearchResultModel> {
        return try {
            val response = apiService.search(
                query = query,
                location = location,
                contentType = contentType,
                page = page,
                pageSize = pageSize,
                isFree = if (isFree == true) 1 else null
            )

            mapper.mapSearchResponse(response)

        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}