package makarova.citypulse.feature.main.api.repository

import makarova.citypulse.feature.main.api.model.SearchResultModel

interface SearchRepository {
    suspend fun searchEvents(
        query: String,
        location: String = "msk",
        contentType: String? = null,
        page: Int = 1,
        pageSize: Int = 20,
        isFree: Boolean? = null
    ): List<SearchResultModel>
}