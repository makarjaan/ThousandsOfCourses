package makarova.citypulse.feature.main.api.usecase

import makarova.citypulse.feature.main.api.model.SearchResultModel

interface SearchEventsUseCase {
    suspend operator fun invoke(
        query: String,
        location: String = "msk",
        contentType: String? = null,
        page: Int = 1,
        pageSize: Int = 20,
        isFree: Boolean? = null
    ): List<SearchResultModel>
}