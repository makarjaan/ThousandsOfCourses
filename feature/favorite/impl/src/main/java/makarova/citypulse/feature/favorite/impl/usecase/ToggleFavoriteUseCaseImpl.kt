package makarova.citypulse.feature.favorite.impl.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import makarova.citypulse.feature.favorite.api.repository.FavoriteEventsRepository
import makarova.citypulse.feature.favorite.api.usecase.ToggleFavoriteUseCase
import makarova.citypulse.feature.main.api.model.EventModel
import javax.inject.Inject

class ToggleFavoriteUseCaseImpl @Inject constructor(
    private val repository: FavoriteEventsRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ToggleFavoriteUseCase {
    
    override suspend fun invoke(event: EventModel, userEmail: String): Boolean {
        return withContext(ioDispatcher) {
            return@withContext repository.toggleFavorite(event, userEmail)
        }
    }
}
