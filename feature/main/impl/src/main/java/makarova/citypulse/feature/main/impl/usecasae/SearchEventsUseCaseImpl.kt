package makarova.citypulse.feature.main.impl.usecasae

import makarova.citypulse.feature.main.api.model.SearchResultModel
import makarova.citypulse.feature.main.api.repository.SearchRepository
import makarova.citypulse.feature.main.api.usecase.SearchEventsUseCase
import javax.inject.Inject

class SearchEventsUseCaseImpl @Inject constructor(
    private val repository: SearchRepository
): SearchEventsUseCase {

    override suspend operator fun invoke(
        query: String,
        location: String,
        contentType: String?,
        page: Int,
        pageSize: Int,
        isFree: Boolean?
    ): List<SearchResultModel> {
        return repository.searchEvents(
            query = query,
            location = location,
            contentType = contentType,
            page = page,
            pageSize = pageSize,
            isFree = isFree
        )
    }
}