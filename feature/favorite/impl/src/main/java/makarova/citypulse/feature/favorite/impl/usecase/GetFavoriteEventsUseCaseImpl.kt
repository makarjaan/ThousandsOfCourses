package makarova.citypulse.feature.favorite.impl.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import makarova.citypulse.feature.favorite.api.repository.FavoriteEventsRepository
import makarova.citypulse.feature.favorite.api.usecase.GetFavoriteEventsUseCase
import makarova.citypulse.feature.main.api.model.EventModel
import javax.inject.Inject

class GetFavoriteEventsUseCaseImpl @Inject constructor(
    private val repository: FavoriteEventsRepository,
    private val ioDispatcher: CoroutineDispatcher
) : GetFavoriteEventsUseCase {
    
    override suspend fun invoke(userEmail: String): List<EventModel> {
        return withContext(ioDispatcher) {
            return@withContext repository.getFavoriteEventsByUser(userEmail)
        }
    }
}