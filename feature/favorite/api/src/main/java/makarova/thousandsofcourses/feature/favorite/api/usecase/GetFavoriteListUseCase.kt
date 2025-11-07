package makarova.thousandsofcourses.feature.favorite.api.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import makarova.thousandsofcourses.feature.main.api.model.CourseModel
import makarova.thousandsofcourses.feature.favorite.api.repository.FavoriteRepository
import javax.inject.Inject

class GetFavoriteListUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository,
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): List<CourseModel> {
        return withContext(ioDispatcher) {
            favoriteRepository.getFavoriteCourses()
        }
    }
}