package makarova.thousandsofcourses.feature.favorite.impl.repository

import makarova.thousandsofcourses.feature.main.api.model.CourseModel
import makarova.thousandsofcourses.database.dao.CourseDao
import makarova.thousandsofcourses.database.dao.UserDao
import makarova.thousandsofcourses.database.dao.UserFavoriteCoursesDao
import makarova.thousandsofcourses.database.mapper.DbMapper
import makarova.thousandsofcourses.feature.favorite.api.repository.FavoriteRepository
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val courseDao: CourseDao,
    private val dbMapper: DbMapper,
    private val userFavoriteCoursesDao: UserFavoriteCoursesDao,
): FavoriteRepository {

    private suspend fun getCurrentUserId(): Long {
        return userDao.getCurrentUserId() ?: throw IllegalStateException("User must be logged in")
    }

    override suspend fun getFavoriteCourses(): List<CourseModel> {
        val userId = getCurrentUserId()
        val favoriteCourseIds = userFavoriteCoursesDao.getFavoriteCoursesByUserId(userId)
        val favoriteCourses = courseDao.getCoursesByIds(favoriteCourseIds)
        return favoriteCourses.map { courseEntity ->
            dbMapper.mapFromCourseEntity(courseEntity).copy(hasLike = true)
        }
    }

}