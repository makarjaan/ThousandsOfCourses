package makarova.citypulse.feature.favorite.impl.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import makarova.citypulse.feature.favorite.api.repository.FavoriteEventsRepository
import makarova.citypulse.feature.favorite.api.usecase.IsFavoriteUseCase
import javax.inject.Inject

class IsFavoriteUseCaseImpl @Inject constructor(
    private val repository: FavoriteEventsRepository,
    private val ioDispatcher: CoroutineDispatcher
) : IsFavoriteUseCase {
    
    override suspend fun invoke(eventId: String, userEmail: String): Boolean {
        return withContext(ioDispatcher) {
            return@withContext repository.isFavorite(eventId, userEmail)
        }
    }
}