package makarova.citypulse.feature.favorite.impl.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import makarova.citypulse.feature.favorite.api.repository.FavoriteEventsRepository
import makarova.citypulse.feature.favorite.api.usecase.GetFavoritesCountUseCase
import javax.inject.Inject

class GetFavoritesCountUseCaseImpl @Inject constructor(
    private val repository: FavoriteEventsRepository,
    private val ioDispatcher: CoroutineDispatcher
) : GetFavoritesCountUseCase {
    
    override suspend fun invoke(userEmail: String): Int {
        return withContext(ioDispatcher) {
            return@withContext repository.getFavoritesCount(userEmail)
        }
    }
}
