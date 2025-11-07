package makarova.thousandsofcourses.database.mapper

import makarova.thousandsofcourses.feature.main.api.model.CourseModel
import makarova.thousandsofcourses.database.entities.CourseEntity
import makarova.thousandsofcourses.database.entities.UserEntity
import makarova.thousandsofcourses.database.entities.UserFavoriteCoursesEntity
import makarova.thousandsofcourses.feature.auth.api.model.UserLoginModel
import javax.inject.Inject

class DbMapper @Inject constructor() {

    // User mapping
    fun mapToUserModel(userEntity: UserEntity): UserLoginModel {
        return UserLoginModel(
            login = userEntity.email,
            password = userEntity.password
        )
    }

    fun mapToUserEntity(userModel: UserLoginModel): UserEntity {
        return UserEntity(
            email = userModel.login,
            password = userModel.password
        )
    }

    // Course mapping

    fun mapToCourseEntities(courseModels: List<CourseModel>): List<CourseEntity> {
        return courseModels.map { mapToCourseEntity(it) }
    }

    fun mapToCourseEntity(courseModel: CourseModel): CourseEntity {
        return CourseEntity(
            id = courseModel.id,
            title = courseModel.title,
            text = courseModel.text,
            price = courseModel.price,
            rate = courseModel.rate,
            startDate = courseModel.startDate,
            publishDate = courseModel.publishDate
        )
    }


    fun mapFromCourseEntities(courseEntities: List<CourseEntity>): List<CourseModel> {
        return courseEntities.map { mapFromCourseEntity(it) }
    }

    fun mapFromCourseEntity(courseEntity: CourseEntity): CourseModel {
        return CourseModel(
            id = courseEntity.id,
            title = courseEntity.title,
            text = courseEntity.text,
            price = courseEntity.price,
            rate = courseEntity.rate,
            startDate = courseEntity.startDate,
            publishDate = courseEntity.publishDate,
            hasLike = false
        )
    }

    // Favorite Course mapper
    fun mapToFavoriteEntity(courseId: Long, userId: Long): UserFavoriteCoursesEntity {
        return UserFavoriteCoursesEntity(
            userId = userId,
            courseId = courseId,
            addedDate = System.currentTimeMillis()
        )
    }

    fun mapToCourseModelsWithFavorite(
        courseModels: List<CourseModel>,
        favoriteStatuses: Map<Long, Boolean>
    ): List<CourseModel> {
        return courseModels.map { courseModel ->
            courseModel.copy(hasLike = favoriteStatuses[courseModel.id] ?: false)
        }
    }

}