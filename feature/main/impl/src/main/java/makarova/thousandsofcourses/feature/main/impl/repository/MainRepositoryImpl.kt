package makarova.thousandsofcourses.feature.main.impl.repository

import makarova.thousandsofcourses.api.model.CourseModel
import makarova.thousandsofcourses.api.repository.MainRepository
import makarova.thousandsofcourses.network.Api
import makarova.thousandsofcourses.network.mapper.ApiResponseMapper
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val api: Api,
    private val mapper: ApiResponseMapper
): MainRepository {

    override suspend fun getCourses(): List<CourseModel> {
        return api.load().let(mapper::mapToListCourses)
    }

}