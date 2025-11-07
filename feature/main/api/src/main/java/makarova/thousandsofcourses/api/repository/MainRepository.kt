package makarova.thousandsofcourses.api.repository

import makarova.thousandsofcourses.api.model.CourseModel

interface MainRepository {

    suspend fun getCourses(): List<CourseModel>

    suspend fun toggleFavorite(course: CourseModel)
}