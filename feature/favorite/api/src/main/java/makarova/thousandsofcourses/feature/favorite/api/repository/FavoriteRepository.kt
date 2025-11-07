package makarova.thousandsofcourses.feature.favorite.api.repository

import makarova.thousandsofcourses.feature.main.api.model.CourseModel

interface FavoriteRepository {

    suspend fun getFavoriteCourses(): List<CourseModel>
}