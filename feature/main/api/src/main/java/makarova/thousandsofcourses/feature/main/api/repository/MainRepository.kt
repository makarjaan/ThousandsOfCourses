package makarova.thousandsofcourses.feature.main.api.repository

import makarova.thousandsofcourses.feature.main.api.model.CourseModel

interface MainRepository {

    suspend fun getCourses(): List<CourseModel>

    suspend fun toggleFavorite(course: CourseModel)
}