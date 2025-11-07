package makarova.thousandsofcourses.feature.main.impl.repository

import android.util.Log
import makarova.thousandsofcourses.api.model.CourseModel
import makarova.thousandsofcourses.api.repository.MainRepository
import makarova.thousandsofcourses.database.dao.CourseDao
import makarova.thousandsofcourses.database.dao.UserDao
import makarova.thousandsofcourses.database.dao.UserFavoriteCoursesDao
import makarova.thousandsofcourses.database.mapper.DbMapper
import makarova.thousandsofcourses.network.Api
import makarova.thousandsofcourses.network.mapper.ApiResponseMapper
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val api: Api,
    private val courseDao: CourseDao,
    private val userDao: UserDao,
    private val dbMapper: DbMapper,
    private val userFavoriteCoursesDao: UserFavoriteCoursesDao,
    private val mapper: ApiResponseMapper
): MainRepository {

    private suspend fun getCurrentUserId(): Long {
        return userDao.getCurrentUserId() ?: throw IllegalStateException("User must be logged in")
    }

    override suspend fun getCourses(): List<CourseModel> {

        val response = api.load()
        val courses = mapper.mapToListCourses(response)

        courseDao.insertCourses(dbMapper.mapToCourseEntities(courses))

        val userId = getCurrentUserId()

        val favoriteStatuses = courses.associate { course ->
            course.id to userFavoriteCoursesDao.isCourseFavorite(userId, course.id)
        }

        return dbMapper.mapToCourseModelsWithFavorite(courses, favoriteStatuses)
    }

    override suspend fun toggleFavorite(course: CourseModel) {
        val userId = getCurrentUserId()

        val isFavorite = userFavoriteCoursesDao.isCourseFavorite(userId, course.id)

        if (isFavorite) {
            userFavoriteCoursesDao.removeFromFavorites(userId, course.id)
        } else {
            val favoriteEntity = dbMapper.mapToFavoriteEntity(course.id, userId.toLong())
            userFavoriteCoursesDao.addToFavorites(favoriteEntity)
        }
    }
}