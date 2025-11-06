package makarova.thousandsofcourses.api.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import makarova.thousandsofcourses.api.model.CourseModel
import makarova.thousandsofcourses.api.repository.MainRepository
import javax.inject.Inject

class GetListOfCoursesUseCase @Inject constructor(
    private val mainRepository: MainRepository,
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): List<CourseModel> {
        return withContext(ioDispatcher) {
            mainRepository.getCourses()
        }
    }
}