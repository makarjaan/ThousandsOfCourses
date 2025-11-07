package makarova.thousandsofcourses.feature.main.api.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import makarova.thousandsofcourses.feature.main.api.model.CourseModel
import makarova.thousandsofcourses.feature.main.api.repository.MainRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val mainRepository: MainRepository,
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(course: CourseModel) {
        return withContext(ioDispatcher) {
            mainRepository.toggleFavorite(course)
        }
    }
}