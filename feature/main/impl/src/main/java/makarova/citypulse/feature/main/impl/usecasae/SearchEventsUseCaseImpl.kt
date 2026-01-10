package makarova.citypulse.feature.main.impl.usecasae

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import makarova.citypulse.feature.main.api.model.SearchResultModel
import makarova.citypulse.feature.main.api.repository.EventsRepository
import makarova.citypulse.feature.main.api.usecase.SearchEventsUseCase
import javax.inject.Inject

class SearchEventsUseCaseImpl @Inject constructor(
    private val repository: EventsRepository,
    private val ioDispatcher: CoroutineDispatcher
): SearchEventsUseCase {

    override suspend operator fun invoke(
        query: String,
        location: String,
        contentType: String?,
        page: Int,
        pageSize: Int,
        isFree: Boolean?
    ): List<SearchResultModel> {
        return withContext(ioDispatcher) {
            return@withContext repository.searchEvents(
                query = query,
                location = location,
                contentType = contentType,
                page = page,
                pageSize = pageSize,
                isFree = isFree
            )
        }
    }
}