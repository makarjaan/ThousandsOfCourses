package makarova.thousandsofcourses.database

import androidx.room.Database
import androidx.room.RoomDatabase
import makarova.thousandsofcourses.database.dao.CourseDao
import makarova.thousandsofcourses.database.dao.UserDao
import makarova.thousandsofcourses.database.dao.UserFavoriteCoursesDao
import makarova.thousandsofcourses.database.entities.CourseEntity
import makarova.thousandsofcourses.database.entities.UserEntity
import makarova.thousandsofcourses.database.entities.UserFavoriteCoursesEntity


@Database(
    entities = [
        UserEntity::class, CourseEntity::class, UserFavoriteCoursesEntity::class],
    version = 2
)
abstract class AppDataBase: RoomDatabase() {
    abstract val userDao: UserDao
    abstract val courseDao: CourseDao
    abstract val userFavoriteCoursesDao: UserFavoriteCoursesDao

    companion object {
        const val DB_LOG_KEY = "AppDb"
    }
}